package com.ordermangement.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "assets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", unique = true, nullable = false, length = 36)
    private String uid;

    @Column(name = "customer_uid", nullable = false)
    private String customerUid;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "size")
    private BigDecimal size;

    @Column(name = "usable_size")
    private BigDecimal usableSize;
}
