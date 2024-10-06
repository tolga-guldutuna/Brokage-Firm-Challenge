package com.ordermangement.util.mapper;

import com.ordermangement.model.dto.AssetDTO;
import com.ordermangement.model.entity.Asset;

import java.util.UUID;

public class AssetMapper {

    private AssetMapper() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static Asset toEntity(AssetDTO dto) {
        Asset asset = new Asset();
        asset.setUid(UUID.randomUUID().toString());
        asset.setCustomerUid(dto.getCustomerUid());
        asset.setAssetName(dto.getAssetName());
        asset.setSize(dto.getSize());
        asset.setUsableSize(dto.getUsableSize());
        return asset;
    }

    public static AssetDTO toDTO(Asset entity) {
        AssetDTO dto = new AssetDTO();
        dto.setUid(entity.getUid());
        dto.setCustomerUid(entity.getCustomerUid());
        dto.setAssetName(entity.getAssetName());
        dto.setSize(entity.getSize());
        dto.setUsableSize(entity.getUsableSize());
        return dto;
    }
}
