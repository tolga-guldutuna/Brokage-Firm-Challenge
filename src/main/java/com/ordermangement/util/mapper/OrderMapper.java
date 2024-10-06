package com.ordermangement.util.mapper;

import com.ordermangement.model.dto.OrderDTO;
import com.ordermangement.model.entity.Order;

import java.util.UUID;

public class OrderMapper {

    private OrderMapper() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setUid(UUID.randomUUID().toString());
        order.setCustomerUid(dto.getCustomerUid());
        order.setAssetName(dto.getAssetName());
        order.setOrderSide(dto.getOrderSide());
        order.setSize(dto.getSize());
        order.setPrice(dto.getPrice());
        order.setStatus(dto.getStatus());
        order.setCreateDate(dto.getCreateDate());
        return order;
    }

    public static OrderDTO toDTO(Order entity) {
        OrderDTO dto = new OrderDTO();
        dto.setUid(entity.getUid());
        dto.setCustomerUid(entity.getCustomerUid());
        dto.setAssetName(entity.getAssetName());
        dto.setOrderSide(entity.getOrderSide());
        dto.setSize(entity.getSize());
        dto.setPrice(entity.getPrice());
        dto.setStatus(entity.getStatus());
        dto.setCreateDate(entity.getCreateDate());
        return dto;
    }
}
