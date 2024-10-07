package com.brokenFirmChallenge.service;

import com.brokenFirmChallenge.model.dto.AssetDTO;
import com.brokenFirmChallenge.model.dto.BaseResponse;

import java.util.List;

public interface AssetProcessService {

    BaseResponse<AssetDTO> createAsset(AssetDTO assetDTO);

    BaseResponse<AssetDTO> updateAsset(AssetDTO assetDTO);

    BaseResponse<Void> deleteAsset(String assetUid);

    BaseResponse<List<AssetDTO>> listAssetsByCustomerUid(String customerUid);
}
