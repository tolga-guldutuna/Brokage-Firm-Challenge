package com.brokenFirmChallenge.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetDTO {
    private String uid;
    private String customerUid;
    private String assetName;
    private BigDecimal size;
    private BigDecimal usableSize;
}
