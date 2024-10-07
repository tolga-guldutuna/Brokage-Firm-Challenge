package com.brokenFirmChallenge.service.impl;

import com.brokenFirmChallenge.util.mapper.OrderMapper;  // Doğru import
import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.dto.CreateOrderRequest;
import com.brokenFirmChallenge.model.dto.OrderDTO;
import com.brokenFirmChallenge.model.entity.Asset;
import com.brokenFirmChallenge.model.entity.Order;
import com.brokenFirmChallenge.model.enums.OrderStatus;
import com.brokenFirmChallenge.repository.AssetRepository;
import com.brokenFirmChallenge.repository.OrderRepository;
import com.brokenFirmChallenge.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public BaseResponse<OrderDTO> createOrder(CreateOrderRequest request) throws Exception {
        List<Asset> tryAssets = assetRepository.findByCustomerUidAndAssetName(request.getCustomerUid(), "TRY");

        // TRY varlığının olup olmadığını kontrol ediyoruz
        if (tryAssets.isEmpty()) {
            return new BaseResponse<>(false, "TRY asset not found for the customer.", null);
        }

        Asset tryAsset = tryAssets.get(0);

        // TRY bakiyesi kontrolü
        if (tryAsset.getUsableSize().compareTo(request.getPrice().multiply(request.getSize())) < 0) {
            return new BaseResponse<>(false, "Insufficient TRY balance.", null);
        }

        // DTO'yu doğrudan doldurmak yerine, OrderMapper kullanılacak
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerUid(request.getCustomerUid());
        orderDTO.setAssetName(request.getAssetName());
        orderDTO.setOrderSide(request.getOrderSide());
        orderDTO.setSize(request.getSize());
        orderDTO.setPrice(request.getPrice());
        orderDTO.setStatus(OrderStatus.PENDING);
        orderDTO.setCreateDate(LocalDateTime.now());

        // DTO'dan entity'ye dönüştürme
        Order order = OrderMapper.toEntity(orderDTO);

        // TRY bakiyesi güncellenir
        assetRepository.updateUsableSizeNative(tryAsset.getUid(), tryAsset.getUsableSize().subtract(request.getPrice().multiply(request.getSize())));

        orderRepository.save(order);
        return new BaseResponse<>(true, "Order created successfully.", OrderMapper.toDTO(order));
    }

    @Override
    public BaseResponse<List<OrderDTO>> listOrdersByCustomerUid(String customerUid, LocalDateTime startDate, LocalDateTime endDate) {
        List<OrderDTO> orders = orderRepository.findByCustomerUidAndCreateDateBetween(customerUid, startDate, endDate)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());

        return new BaseResponse<>(true, "Orders retrieved successfully.", orders);
    }

    @Override
    public BaseResponse<List<OrderDTO>> listOrdersByCustomerUidAndStatus(String customerUid, OrderStatus status) {
        List<OrderDTO> orders = orderRepository.findByCustomerUidAndStatus(customerUid, status)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());

        return new BaseResponse<>(true, "Orders retrieved successfully.", orders);
    }

    @Override
    public BaseResponse<Void> cancelOrder(String orderUid) throws Exception {
        Order order = orderRepository.findByUid(orderUid);
        if (order.getStatus() != OrderStatus.PENDING) {
            return new BaseResponse<>(false, "Only pending orders can be cancelled.", null);
        }

        orderRepository.cancelPendingOrderNative(orderUid);

        Asset tryAsset = assetRepository.findByCustomerUidAndAssetName(order.getCustomerUid(), "TRY").get(0);
        assetRepository.updateUsableSizeNative(tryAsset.getUid(), tryAsset.getUsableSize().add(order.getPrice().multiply(order.getSize())));

        return new BaseResponse<>(true, "Order cancelled successfully.", null);
    }

    @Override
    public BaseResponse<Void> matchOrder(String orderUid) throws Exception {
        Order order = orderRepository.findByUid(orderUid);
        if (order.getStatus() != OrderStatus.PENDING) {
            return new BaseResponse<>(false, "Only pending orders can be matched.", null);
        }

        Asset asset = assetRepository.findByCustomerUidAndAssetName(order.getCustomerUid(), order.getAssetName()).get(0);
        if (asset == null) {
            return new BaseResponse<>(false, "Asset not found.", null);
        }

        assetRepository.updateUsableSizeNative(asset.getUid(), asset.getUsableSize().add(order.getSize()));

        order.setStatus(OrderStatus.MATCHED);
        orderRepository.save(order);

        return new BaseResponse<>(true, "Order matched successfully.", null);
    }
}
