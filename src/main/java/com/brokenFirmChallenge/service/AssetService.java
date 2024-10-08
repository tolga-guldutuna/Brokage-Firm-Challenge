package com.brokenFirmChallenge.service;

import com.brokenFirmChallenge.model.dto.AssetDTO;
import com.brokenFirmChallenge.model.dto.BaseResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AssetService {

    BaseResponse<List<AssetDTO>> listAssetsByCustomerUid(String customerUid);

    BaseResponse<AssetDTO> getAssetByCustomerUidAndAssetName(String customerUid, String assetName);

    BaseResponse<AssetDTO> getAssetByUid(String assetUid);

    BaseResponse<Void> updateUsableSize(String assetUid, BigDecimal amount);

    BaseResponse<List<AssetDTO>> getAllAssets();
}
