package com.ordermangement.controller;

import com.ordermangement.dto.BaseResponse;
import com.ordermangement.dto.CustomerDTO;
import com.ordermangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{uid}")
    public ResponseEntity<BaseResponse<CustomerDTO>> getCustomer(@PathVariable String uid) {
        CustomerDTO customer = customerService.getCustomerByUid(uid);
        BaseResponse<CustomerDTO> response = new BaseResponse<>(true, "Customer retrieved successfully", customer);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{uid}/deposit")
    public ResponseEntity<BaseResponse<Void>> depositMoney(@PathVariable String uid, @RequestParam BigDecimal amount) {
        customerService.depositMoney(uid, amount);
        BaseResponse<Void> response = new BaseResponse<>(true, "Deposit successful", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{uid}/withdraw")
    public ResponseEntity<BaseResponse<Void>> withdrawMoney(@PathVariable String uid, @RequestParam BigDecimal amount, @RequestParam String iban) {
        customerService.withdrawMoney(uid, amount, iban);
        BaseResponse<Void> response = new BaseResponse<>(true, "Withdrawal successful", null);
        return ResponseEntity.ok(response);
    }
}
