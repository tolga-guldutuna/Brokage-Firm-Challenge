package com.ordermangement.service.impl;

import com.ordermangement.dto.CreateOrderRequest;
import com.ordermangement.dto.OrderDTO;
import com.ordermangement.entity.Asset;
import com.ordermangement.entity.Order;
import com.ordermangement.enums.OrderStatus;
import com.ordermangement.repository.AssetRepository;
import com.ordermangement.repository.OrderRepository;
import com.ordermangement.service.OrderService;
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
    public void createOrder(CreateOrderRequest request) {
        Asset asset = assetRepository.findByCustomerUidAndAssetName(request.getCustomerUid(), "TRY").stream().findFirst().orElse(null);
        if (asset == null || asset.getUsableSize().compareTo(request.getPrice().multiply(request.getSize())) < 0) {
            throw new RuntimeException("Insufficient funds");
        } else {
            asset.setUsableSize(asset.getUsableSize().subtract(request.getPrice().multiply(request.getSize())));
            assetRepository.save(asset);

            Order order = new Order();
            order.setCustomerUid(request.getCustomerUid());
            order.setAssetName(request.getAssetName());
            order.setOrderSide(request.getOrderSide());
            order.setSize(request.getSize());
            order.setPrice(request.getPrice());
            order.setStatus(OrderStatus.PENDING);
            orderRepository.save(order);
        }
    }

    @Override
    public List<OrderDTO> listOrders(String customerUid, LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByCustomerUidAndCreateDateBetween(customerUid, startDate, endDate);
        return orders.stream().map(order -> {
            OrderDTO dto = new OrderDTO();
            dto.setUid(order.getUid());
            dto.setCustomerUid(order.getCustomerUid());
            dto.setAssetName(order.getAssetName());
            dto.setOrderSide(order.getOrderSide());
            dto.setSize(order.getSize());
            dto.setPrice(order.getPrice());
            dto.setStatus(order.getStatus());
            dto.setCreateDate(order.getCreateDate());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void cancelOrder(String orderUid) {
        Order order = orderRepository.findByUid(orderUid);
        if (order == null || order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Order cannot be canceled");
        } else {
            order.setStatus(OrderStatus.CANCELED);
            orderRepository.save(order);

            Asset asset = assetRepository.findByCustomerUidAndAssetName(order.getCustomerUid(), "TRY").stream().findFirst().orElse(null);
            if (asset != null) {
                asset.setUsableSize(asset.getUsableSize().add(order.getPrice().multiply(order.getSize())));
                assetRepository.save(asset);
            }
        }
    }
}
