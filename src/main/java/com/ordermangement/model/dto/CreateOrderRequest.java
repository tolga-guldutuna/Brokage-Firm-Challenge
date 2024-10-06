package com.ordermangement.model.dto;

import com.ordermangement.model.enums.OrderSide;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderRequest {
    private String customerUid;
    private String assetName;
    private OrderSide orderSide;
    private BigDecimal size;
    private BigDecimal price;
}
