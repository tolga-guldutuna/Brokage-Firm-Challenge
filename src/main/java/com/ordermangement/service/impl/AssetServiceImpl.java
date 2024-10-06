package com.ordermangement.service.impl;

import com.ordermangement.util.mapper.AssetMapper;
import com.ordermangement.model.dto.AssetDTO;
import com.ordermangement.model.dto.BaseResponse;
import com.ordermangement.model.entity.Asset;
import com.ordermangement.repository.AssetRepository;
import com.ordermangement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public BaseResponse<List<AssetDTO>> listAssetsByCustomerUid(String customerUid) {
        List<AssetDTO> assets = assetRepository.findByCustomerUid(customerUid)
                .stream()
                .map(AssetMapper::toDTO)
                .collect(Collectors.toList());

        return new BaseResponse<>(true, "Assets retrieved successfully.", assets);
    }

    @Override
    public BaseResponse<AssetDTO> getAssetByCustomerUidAndAssetName(String customerUid, String assetName) {
        Asset asset = assetRepository.findByCustomerUidAndAssetName(customerUid, assetName).get(0);
        if (asset == null) {
            return new BaseResponse<>(false, "Asset not found.", null);
        }

        return new BaseResponse<>(true, "Asset retrieved successfully.", AssetMapper.toDTO(asset));
    }

    @Override
    public BaseResponse<AssetDTO> getAssetByUid(String assetUid) {
        Asset asset = assetRepository.findByUid(assetUid);
        if (asset == null) {
            return new BaseResponse<>(false, "Asset not found.", null);
        }

        return new BaseResponse<>(true, "Asset retrieved successfully.", AssetMapper.toDTO(asset));
    }

    @Override
    public BaseResponse<Void> updateUsableSize(String assetUid, BigDecimal amount) {
        Asset asset = assetRepository.findByUid(assetUid);
        if (asset == null) {
            return new BaseResponse<>(false, "Asset not found.", null);
        }

        assetRepository.updateUsableSizeNative(assetUid, amount);
        return new BaseResponse<>(true, "Usable size updated successfully.", null);
    }
}
