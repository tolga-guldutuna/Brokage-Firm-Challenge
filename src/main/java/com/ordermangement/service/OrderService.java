package com.ordermangement.service;

import com.ordermangement.dto.CreateOrderRequest;
import com.ordermangement.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void createOrder(CreateOrderRequest request);

    List<OrderDTO> listOrders(String customerUid, LocalDateTime startDate, LocalDateTime endDate);

    void cancelOrder(String orderUid);
}
