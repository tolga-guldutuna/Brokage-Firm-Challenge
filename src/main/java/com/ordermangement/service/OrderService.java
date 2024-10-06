package com.ordermangement.service;

import com.ordermangement.model.dto.BaseResponse;
import com.ordermangement.model.dto.CreateOrderRequest;
import com.ordermangement.model.dto.OrderDTO;
import com.ordermangement.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    BaseResponse<OrderDTO> createOrder(CreateOrderRequest request) throws Exception;

    BaseResponse<List<OrderDTO>> listOrdersByCustomerUid(String customerUid, LocalDateTime startDate, LocalDateTime endDate);

    BaseResponse<List<OrderDTO>> listOrdersByCustomerUidAndStatus(String customerUid, OrderStatus status);

    BaseResponse<Void> cancelOrder(String orderUid) throws Exception;

    BaseResponse<Void> matchOrder(String orderUid) throws Exception;
}
