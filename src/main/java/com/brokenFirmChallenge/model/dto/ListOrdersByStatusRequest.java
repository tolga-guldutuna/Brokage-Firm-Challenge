package com.brokenFirmChallenge.model.dto;

import com.brokenFirmChallenge.model.enums.OrderStatus;
import lombok.Data;

@Data
public class ListOrdersByStatusRequest {
    private String customerUid;
    private OrderStatus status;
}
