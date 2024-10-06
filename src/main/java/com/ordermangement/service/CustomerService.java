package com.ordermangement.service;

import com.ordermangement.model.dto.CustomerDTO;

import java.math.BigDecimal;

public interface CustomerService {
    CustomerDTO getCustomerByUid(String uid);

    void depositMoney(String customerUid, BigDecimal amount);

    void withdrawMoney(String customerUid, BigDecimal amount, String iban);
}
