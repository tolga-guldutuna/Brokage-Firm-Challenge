package com.brokenFirmChallenge.controller;

import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.dto.CustomerDTO;
import com.brokenFirmChallenge.service.CustomerProcessService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processes/customers")
public class CustomerProcessController {

    @Autowired
    private CustomerProcessService customerProcessService;

    @Operation(summary = "Create a new customer")
    @PostMapping("/create")
    public BaseResponse<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerProcessService.createCustomer(customerDTO);
    }

    @Operation(summary = "Update an existing customer")
    @PutMapping("/update")
    public BaseResponse<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerProcessService.updateCustomer(customerDTO);
    }

    @Operation(summary = "Delete a customer by UID")
    @DeleteMapping("/delete")
    public BaseResponse<Void> deleteCustomer(@RequestBody String customerUid) {
        return customerProcessService.deleteCustomer(customerUid);
    }

    @Operation(summary = "Change customer password")
    @PutMapping("/change-password")
    public BaseResponse<Void> changePassword(
            @RequestParam String customerUid,
            @RequestParam String newPassword) {
        return customerProcessService.changePassword(customerUid, newPassword);
    }

    @Operation(summary = "Forgot password service (send reset link)")
    @PostMapping("/forgot-password")
    public BaseResponse<Void> forgotPassword(@RequestBody String email) {
        return customerProcessService.forgotPassword(email);
    }
}
