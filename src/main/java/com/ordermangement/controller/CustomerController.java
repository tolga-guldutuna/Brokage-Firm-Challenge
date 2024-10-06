package com.ordermangement.controller;

import com.ordermangement.model.dto.BaseResponse;
import com.ordermangement.model.dto.CustomerDTO;
import com.ordermangement.model.dto.DepositMoneyRequest;
import com.ordermangement.model.dto.WithdrawMoneyRequest;
import com.ordermangement.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Get customer by UID")
    @PostMapping("/get-by-uid")
    public BaseResponse<CustomerDTO> getCustomerByUid(@RequestBody String customerUid) {
        return customerService.getCustomerByUid(customerUid);
    }

    @Operation(summary = "Get customer by email")
    @PostMapping("/get-by-email")
    public BaseResponse<CustomerDTO> getCustomerByEmail(@RequestBody String email) {
        return customerService.getCustomerByEmail(email);
    }

    @Operation(summary = "Deposit money for a customer")
    @PostMapping("/deposit")
    public BaseResponse<Void> depositMoney(@RequestBody DepositMoneyRequest request) throws Exception {
        return customerService.depositMoney(request.getCustomerUid(), request.getAmount());
    }

    @Operation(summary = "Withdraw money for a customer")
    @PostMapping("/withdraw")
    public BaseResponse<Void> withdrawMoney(@RequestBody WithdrawMoneyRequest request) throws Exception {
        return customerService.withdrawMoney(request.getCustomerUid(), request.getAmount(), request.getIban());
    }
}
