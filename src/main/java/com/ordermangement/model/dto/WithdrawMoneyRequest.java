package com.ordermangement.model.dto;

import lombok.Data;

@Data
public class WithdrawMoneyRequest {
    private String customerUid;
    private double amount;
    private String iban;
}
