package com.brokenFirmChallenge.service.impl;

import com.brokenFirmChallenge.util.mapper.CustomerMapper;
import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.dto.CustomerDTO;
import com.brokenFirmChallenge.model.entity.Asset;
import com.brokenFirmChallenge.model.entity.Customer;
import com.brokenFirmChallenge.repository.AssetRepository;
import com.brokenFirmChallenge.repository.CustomerRepository;
import com.brokenFirmChallenge.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public BaseResponse<CustomerDTO> getCustomerByUid(String customerUid) {
        Customer customer = customerRepository.findByUid(customerUid);
        if (customer == null) {
            return new BaseResponse<>(false, "Customer not found.", null);
        }
        return new BaseResponse<>(true, "Customer retrieved successfully.", CustomerMapper.toDTO(customer));
    }

    @Override
    public BaseResponse<CustomerDTO> getCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            return new BaseResponse<>(false, "Customer not found by email.", null);
        }
        return new BaseResponse<>(true, "Customer retrieved successfully.", CustomerMapper.toDTO(customer));
    }

    @Override
    public BaseResponse<Void> depositMoney(String customerUid, double amount) throws Exception {
        List<Asset> tryAssets = assetRepository.findByCustomerUidAndAssetName(customerUid, "TRY");
        if (tryAssets.isEmpty()) {
            return new BaseResponse<>(false, "TRY asset not found for customer.", null);
        }

        Asset tryAsset = tryAssets.get(0);
        assetRepository.updateUsableSizeNative(tryAsset.getUid(), tryAsset.getUsableSize().add(BigDecimal.valueOf(amount)));
        return new BaseResponse<>(true, "Money deposited successfully.", null);
    }

    @Override
    public BaseResponse<Void> withdrawMoney(String customerUid, double amount, String iban) throws Exception {
        List<Asset> tryAssets = assetRepository.findByCustomerUidAndAssetName(customerUid, "TRY");
        if (tryAssets.isEmpty()) {
            return new BaseResponse<>(false, "TRY asset not found for customer.", null);
        }

        Asset tryAsset = tryAssets.get(0);

        if (tryAsset.getUsableSize().compareTo(BigDecimal.valueOf(amount)) < 0) {
            return new BaseResponse<>(false, "Insufficient TRY balance.", null);
        }

        assetRepository.updateUsableSizeNative(tryAsset.getUid(), tryAsset.getUsableSize().subtract(BigDecimal.valueOf(amount)));
        return new BaseResponse<>(true, "Money withdrawn successfully.", null);
    }

    @Override
    public BaseResponse<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(CustomerMapper::toDTO)
                .collect(Collectors.toList());
        return new BaseResponse<>(true, "All customers retrieved successfully", customerDTOs);
    }

}
