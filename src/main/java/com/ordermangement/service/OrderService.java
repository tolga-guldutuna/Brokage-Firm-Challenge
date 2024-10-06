package com.ordermangement.service;

import com.ordermangement.model.dto.CreateOrderRequest;
import com.ordermangement.model.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void createOrder(CreateOrderRequest request);

    List<OrderDTO> listOrders(String customerUid, LocalDateTime startDate, LocalDateTime endDate);

    void cancelOrder(String orderUid);
}
