package com.brokenFirmChallenge.service;

import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    BaseResponse<CustomerDTO> getCustomerByUid(String customerUid);

    BaseResponse<CustomerDTO> getCustomerByEmail(String email);

    BaseResponse<Void> depositMoney(String customerUid, double amount) throws Exception;

    BaseResponse<Void> withdrawMoney(String customerUid, double amount, String iban) throws Exception;

    BaseResponse<List<CustomerDTO>> getAllCustomers();
}
