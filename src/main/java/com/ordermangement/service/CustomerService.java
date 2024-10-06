package com.ordermangement.service;

import com.ordermangement.model.dto.BaseResponse;
import com.ordermangement.model.dto.CustomerDTO;

public interface CustomerService {

    BaseResponse<CustomerDTO> getCustomerByUid(String customerUid);

    BaseResponse<CustomerDTO> getCustomerByEmail(String email);

    BaseResponse<Void> depositMoney(String customerUid, double amount) throws Exception;

    BaseResponse<Void> withdrawMoney(String customerUid, double amount, String iban) throws Exception;
}
