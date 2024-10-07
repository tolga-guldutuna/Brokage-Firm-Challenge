package com.brokenFirmChallenge.controller;

import com.brokenFirmChallenge.model.dto.*;
import com.brokenFirmChallenge.service.OrderService;
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

    @Operation(summary = "List orders for a customer by status")
    @PostMapping("/list-by-status")
    public BaseResponse<List<OrderDTO>> listOrdersByCustomerUidAndStatus(@RequestBody ListOrdersByStatusRequest request) {
        return orderService.listOrdersByCustomerUidAndStatus(request.getCustomerUid(), request.getStatus());
    }

    @GetMapping("/all")
    public BaseResponse<List<OrderDTO>> getAllOrders() {
        return orderService.getAllOrders();
    }
}
