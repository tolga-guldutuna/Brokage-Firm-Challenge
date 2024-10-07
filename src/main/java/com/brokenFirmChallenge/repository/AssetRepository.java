package com.brokenFirmChallenge.repository;

import com.brokenFirmChallenge.model.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findByCustomerUid(String customerUid);
    List<Asset> findByCustomerUidAndAssetName(String customerUid, String assetName);
    Asset findByUid(String uid);
    @Modifying
    @Transactional
    @Query(value = "UPDATE assets SET usable_size = usable_size + :amount WHERE uid = :assetUid", nativeQuery = true)
    void updateUsableSizeNative(@Param("assetUid") String assetUid, @Param("amount") BigDecimal amount);
}
