package com.ordermangement.controller;

import com.ordermangement.model.dto.BaseResponse;
import com.ordermangement.model.dto.OrderUidRequest;
import com.ordermangement.model.dto.CreateOrderRequest;
import com.ordermangement.model.dto.ListOrdersRequest;
import com.ordermangement.model.dto.OrderDTO;
import com.ordermangement.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Create a new order for a customer")
    @PostMapping("/create")
    public BaseResponse<OrderDTO> createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        return orderService.createOrder(request);
    }

    @Operation(summary = "List orders for a customer by date range")
    @PostMapping("/list")
    public BaseResponse<List<OrderDTO>> listOrdersByCustomerUid(@RequestBody ListOrdersRequest request) {
        return orderService.listOrdersByCustomerUid(request.getCustomerUid(), request.getStartDate(), request.getEndDate());
    }

    @Operation(summary = "Cancel a pending order")
    @PostMapping("/cancel")
    public BaseResponse<Void> cancelOrder(@RequestBody OrderUidRequest request) throws Exception {
        return orderService.cancelOrder(request.getOrderUid());
    }

    @Operation(summary = "Match a pending order (Admin Only)")
    @PostMapping("/match")
    public BaseResponse<Void> matchOrder(@RequestBody OrderUidRequest request) throws Exception {
        return orderService.matchOrder(request.getOrderUid());
    }
}
