package com.ordermangement.controller;

import com.ordermangement.dto.BaseResponse;
import com.ordermangement.dto.CreateOrderRequest;
import com.ordermangement.dto.OrderDTO;
import com.ordermangement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createOrder(@RequestBody CreateOrderRequest request) {
        orderService.createOrder(request);
        BaseResponse<Void> response = new BaseResponse<>(true, "Order created successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<OrderDTO>>> listOrders(@RequestParam String customerUid,
                                                                   @RequestParam(required = false) LocalDateTime startDate,
                                                                   @RequestParam(required = false) LocalDateTime endDate) {
        List<OrderDTO> orders = orderService.listOrders(customerUid, startDate, endDate);
        BaseResponse<List<OrderDTO>> response = new BaseResponse<>(true, "Orders retrieved successfully", orders);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderUid}")
    public ResponseEntity<BaseResponse<Void>> cancelOrder(@PathVariable String orderUid) {
        orderService.cancelOrder(orderUid);
        BaseResponse<Void> response = new BaseResponse<>(true, "Order canceled successfully", null);
        return ResponseEntity.ok(response);
    }
}
