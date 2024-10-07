package com.brokenFirmChallenge.service.impl;

import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.dto.CustomerDTO;
import com.brokenFirmChallenge.model.entity.Customer;
import com.brokenFirmChallenge.repository.CustomerRepository;
import com.brokenFirmChallenge.service.CustomerProcessService;
import com.brokenFirmChallenge.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerProcessServiceImpl implements CustomerProcessService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<CustomerDTO> createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.findByEmail(customerDTO.getEmail()) != null) {
            return new BaseResponse<>(false, "Email already exists.", null);
        }

        String hashedPassword = passwordEncoder.encode(customerDTO.getPassword());
        customerDTO.setPassword(hashedPassword);

        Customer customer = CustomerMapper.toEntity(customerDTO);
        customerRepository.save(customer);

        return new BaseResponse<>(true, "Customer created successfully.", CustomerMapper.toDTO(customer));
    }

    @Override
    public BaseResponse<CustomerDTO> updateCustomer(CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findByUid(customerDTO.getUid());
        if (existingCustomer == null) {
            return new BaseResponse<>(false, "Customer not found.", null);
        }

        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setEmail(customerDTO.getEmail());
        customerRepository.save(existingCustomer);

        return new BaseResponse<>(true, "Customer updated successfully.", CustomerMapper.toDTO(existingCustomer));
    }

    @Override
    public BaseResponse<Void> deleteCustomer(String customerUid) {
        Customer customer = customerRepository.findByUid(customerUid);
        if (customer == null) {
            return new BaseResponse<>(false, "Customer not found.", null);
        }

        customerRepository.delete(customer);
        return new BaseResponse<>(true, "Customer deleted successfully.", null);
    }

    @Override
    public BaseResponse<Void> changePassword(String customerUid, String newPassword) {
        Customer customer = customerRepository.findByUid(customerUid);
        if (customer == null) {
            return new BaseResponse<>(false, "Customer not found.", null);
        }

        String hashedPassword = passwordEncoder.encode(newPassword);
        customer.setPassword(hashedPassword);
        customerRepository.save(customer);

        return new BaseResponse<>(true, "Password updated successfully.", null);
    }

    @Override
    public BaseResponse<Void> forgotPassword(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            return new BaseResponse<>(false, "Customer not found.", null);
        }

        // Şifre sıfırlama işlemi burada yapılacak
        return new BaseResponse<>(true, "Password reset email sent.", null);
    }
}
