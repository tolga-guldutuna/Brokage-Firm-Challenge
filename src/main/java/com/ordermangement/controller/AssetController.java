package com.ordermangement.controller;

import com.ordermangement.model.dto.BaseResponse;
import com.ordermangement.model.dto.AssetDTO;
import com.ordermangement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/{customerUid}")
    public ResponseEntity<BaseResponse<List<AssetDTO>>> getAssetsByCustomer(@PathVariable String customerUid) {
        List<AssetDTO> assets = assetService.getAssetsByCustomerUid(customerUid);
        BaseResponse<List<AssetDTO>> response = new BaseResponse<>(true, "Assets retrieved successfully", assets);
        return ResponseEntity.ok(response);
    }
}
