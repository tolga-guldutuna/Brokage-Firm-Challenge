package com.ordermangement.service.impl;

import com.ordermangement.dto.AssetDTO;
import com.ordermangement.entity.Asset;
import com.ordermangement.repository.AssetRepository;
import com.ordermangement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public List<AssetDTO> getAssetsByCustomerUid(String customerUid) {
        List<Asset> assets = assetRepository.findByCustomerUid(customerUid);
        return assets.stream().map(asset -> {
            AssetDTO dto = new AssetDTO();
            dto.setUid(asset.getUid());
            dto.setCustomerUid(asset.getCustomerUid());
            dto.setAssetName(asset.getAssetName());
            dto.setSize(asset.getSize());
            dto.setUsableSize(asset.getUsableSize());
            return dto;
        }).collect(Collectors.toList());
    }
}
