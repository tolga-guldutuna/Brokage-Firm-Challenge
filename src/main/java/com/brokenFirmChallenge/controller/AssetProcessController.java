package com.brokenFirmChallenge.controller;

import com.brokenFirmChallenge.model.dto.AssetDTO;
import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.service.AssetProcessService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processes/assets")
public class AssetProcessController {

    @Autowired
    private AssetProcessService assetProcessService;

    @Operation(summary = "Create a new asset for a customer")
    @PostMapping("/create")
    public BaseResponse<AssetDTO> createAsset(@RequestBody AssetDTO assetDTO) {
        return assetProcessService.createAsset(assetDTO);
    }

    @Operation(summary = "Update an existing asset")
    @PutMapping("/update")
    public BaseResponse<AssetDTO> updateAsset(@RequestBody AssetDTO assetDTO) {
        return assetProcessService.updateAsset(assetDTO);
    }

    @Operation(summary = "Delete an asset by UID")
    @DeleteMapping("/delete")
    public BaseResponse<Void> deleteAsset(@RequestBody String assetUid) {
        return assetProcessService.deleteAsset(assetUid);
    }

    @Operation(summary = "List all assets for a given customer")
    @PostMapping("/list")
    public BaseResponse<List<AssetDTO>> listAssetsByCustomerUid(@RequestBody String customerUid) {
        return assetProcessService.listAssetsByCustomerUid(customerUid);
    }
}
