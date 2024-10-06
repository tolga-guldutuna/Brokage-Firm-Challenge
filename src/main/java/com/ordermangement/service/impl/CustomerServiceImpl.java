package com.ordermangement.service.impl;

import com.ordermangement.model.dto.CustomerDTO;
import com.ordermangement.model.entity.Asset;
import com.ordermangement.model.entity.Customer;
import com.ordermangement.repository.AssetRepository;
import com.ordermangement.repository.CustomerRepository;
import com.ordermangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public CustomerDTO getCustomerByUid(String uid) {
        Customer customer = customerRepository.findByUid(uid);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setUid(String.valueOf(customer.getUid()));
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        return dto;
    }

    @Override
    public void depositMoney(String customerUid, BigDecimal amount) {
        Asset asset = assetRepository.findByCustomerUidAndAssetName(customerUid, "TRY").stream().findFirst().orElse(null);
        if (asset == null) {
            asset = new Asset();
            asset.setCustomerUid(customerUid);
            asset.setAssetName("TRY");
            asset.setSize(amount);
            asset.setUsableSize(amount);
            assetRepository.save(asset);
        } else {
            asset.setSize(asset.getSize().add(amount));
            asset.setUsableSize(asset.getUsableSize().add(amount));
            assetRepository.save(asset);
        }
    }

    @Override
    public void withdrawMoney(String customerUid, BigDecimal amount, String iban) {
        Asset asset = assetRepository.findByCustomerUidAndAssetName(customerUid, "TRY").stream().findFirst().orElse(null);
        if (asset == null || asset.getUsableSize().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        } else {
            asset.setSize(asset.getSize().subtract(amount));
            asset.setUsableSize(asset.getUsableSize().subtract(amount));
            assetRepository.save(asset);
        }
    }
}
