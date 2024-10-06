package com.ordermangement.service;

import com.ordermangement.dto.AssetDTO;

import java.util.List;

public interface AssetService {
    List<AssetDTO> getAssetsByCustomerUid(String customerUid);
}
