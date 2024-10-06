package com.ordermangement.repository;

import com.ordermangement.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByCustomerUid(String customerUid);
    List<Asset> findByCustomerUidAndAssetName(String customerUid, String assetName);
    Asset findByUid(String uid);
}
