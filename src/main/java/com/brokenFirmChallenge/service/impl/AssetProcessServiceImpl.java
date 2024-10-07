package com.brokenFirmChallenge.service.impl;

import com.brokenFirmChallenge.model.dto.AssetDTO;
import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.model.entity.Asset;
import com.brokenFirmChallenge.repository.AssetRepository;
import com.brokenFirmChallenge.service.AssetProcessService;
import com.brokenFirmChallenge.util.mapper.AssetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetProcessServiceImpl implements AssetProcessService {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public BaseResponse<AssetDTO> createAsset(AssetDTO assetDTO) {
        Asset asset = AssetMapper.toEntity(assetDTO);
        assetRepository.save(asset);
        return new BaseResponse<>(true, "Asset created successfully.", AssetMapper.toDTO(asset));
    }

    @Override
    public BaseResponse<AssetDTO> updateAsset(AssetDTO assetDTO) {
        Asset existingAsset = assetRepository.findByUid(assetDTO.getUid());
        if (existingAsset == null) {
            return new BaseResponse<>(false, "Asset not found.", null);
        }

        existingAsset.setAssetName(assetDTO.getAssetName());
        existingAsset.setSize(assetDTO.getSize());
        existingAsset.setUsableSize(assetDTO.getUsableSize());
        assetRepository.save(existingAsset);

        return new BaseResponse<>(true, "Asset updated successfully.", AssetMapper.toDTO(existingAsset));
    }

    @Override
    public BaseResponse<Void> deleteAsset(String assetUid) {
        Asset asset = assetRepository.findByUid(assetUid);
        if (asset == null) {
            return new BaseResponse<>(false, "Asset not found.", null);
        }

        assetRepository.delete(asset);
        return new BaseResponse<>(true, "Asset deleted successfully.", null);
    }

    @Override
    public BaseResponse<List<AssetDTO>> listAssetsByCustomerUid(String customerUid) {
        List<Asset> assets = assetRepository.findByCustomerUid(customerUid);
        List<AssetDTO> assetDTOs = assets.stream().map(AssetMapper::toDTO).collect(Collectors.toList());
        return new BaseResponse<>(true, "Assets retrieved successfully.", assetDTOs);
    }
}
