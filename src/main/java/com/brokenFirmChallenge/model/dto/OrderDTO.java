package com.brokenFirmChallenge.model.dto;

import com.brokenFirmChallenge.model.enums.OrderSide;
import com.brokenFirmChallenge.model.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private String uid;
    private String customerUid;
    private String assetName;
    private OrderSide orderSide;
    private BigDecimal size;
    private BigDecimal price;
    private OrderStatus status;
    private LocalDateTime createDate;
}
