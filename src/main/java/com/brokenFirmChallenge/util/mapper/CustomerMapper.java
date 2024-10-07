package com.brokenFirmChallenge.util.mapper;

import com.brokenFirmChallenge.model.dto.CustomerDTO;
import com.brokenFirmChallenge.model.entity.Customer;

import java.util.UUID;

public class CustomerMapper {

    private CustomerMapper() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setUid(UUID.randomUUID().toString());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return customer;
    }

    public static CustomerDTO toDTO(Customer entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setUid(entity.getUid());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}
