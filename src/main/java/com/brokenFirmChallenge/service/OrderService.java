package com.brokenFirmChallenge.service;

import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.dto.CreateOrderRequest;
import com.brokenFirmChallenge.model.dto.OrderDTO;
import com.brokenFirmChallenge.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    BaseResponse<OrderDTO> createOrder(CreateOrderRequest request) throws Exception;

    BaseResponse<List<OrderDTO>> listOrdersByCustomerUid(String customerUid, LocalDateTime startDate, LocalDateTime endDate);

    BaseResponse<List<OrderDTO>> listOrdersByCustomerUidAndStatus(String customerUid, OrderStatus status);

    BaseResponse<Void> cancelOrder(String orderUid) throws Exception;

    BaseResponse<Void> matchOrder(String orderUid) throws Exception;
}
