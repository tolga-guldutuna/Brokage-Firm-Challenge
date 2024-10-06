package com.ordermangement.model.dto;

import lombok.Data;

@Data
public class DepositMoneyRequest {
    private String customerUid;
    private double amount;
}
