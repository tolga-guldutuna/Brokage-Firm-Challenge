package com.ordermangement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "assets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", unique = true, nullable = false, updatable = false, length = 36)
    private String uid;

    @Column(name = "customer_uid")
    private String customerUid;

    @Column(name = "asset_name")
    private String assetName;

    @Column(name = "size")
    private BigDecimal size;

    @Column(name = "usable_size")
    private BigDecimal usableSize;

    @PrePersist
    public void prePersist() {
        if (uid == null) {
            uid = UUID.randomUUID().toString();
        }
    }
}
