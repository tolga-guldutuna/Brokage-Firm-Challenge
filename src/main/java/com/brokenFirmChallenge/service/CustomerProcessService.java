package com.brokenFirmChallenge.service;

import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.dto.CustomerDTO;

public interface CustomerProcessService {

    BaseResponse<CustomerDTO> createCustomer(CustomerDTO customerDTO);

    BaseResponse<CustomerDTO> updateCustomer(CustomerDTO customerDTO);

    BaseResponse<Void> deleteCustomer(String customerUid);

    BaseResponse<Void> changePassword(String customerUid, String newPassword);

    BaseResponse<Void> forgotPassword(String email);
}
