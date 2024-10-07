package com.brokenFirmChallenge.controller;

import com.brokenFirmChallenge.model.dto.AssetDTO;
import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @Operation(summary = "List all assets for a given customer")
    @PostMapping("/list")
    public BaseResponse<List<AssetDTO>> listAssetsByCustomerUid(@RequestBody String customerUid) {
        return assetService.listAssetsByCustomerUid(customerUid);
    }

    @Operation(summary = "Get a specific asset by customer UID and asset name")
    @PostMapping("/get")
    public BaseResponse<AssetDTO> getAssetByCustomerUidAndAssetName(
            @RequestParam String customerUid,
            @RequestParam String assetName) {
        return assetService.getAssetByCustomerUidAndAssetName(customerUid, assetName);
    }

    @Operation(summary = "Get an asset by its UID")
    @PostMapping("/get-asset")
    public BaseResponse<AssetDTO> getAssetByUid(@RequestBody String assetUid) {
        return assetService.getAssetByUid(assetUid);
    }

    @Operation(summary = "Update the usable size of an asset")
    @PutMapping("/update-usable-size")
    public BaseResponse<Void> updateUsableSize(
            @RequestParam String assetUid,
            @RequestParam BigDecimal amount) {
        return assetService.updateUsableSize(assetUid, amount);
    }
}
