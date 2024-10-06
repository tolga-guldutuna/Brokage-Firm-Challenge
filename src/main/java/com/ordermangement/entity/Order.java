package com.ordermangement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ordermangement.enums.OrderSide;
import com.ordermangement.enums.OrderStatus;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", unique = true, nullable = false, updatable = false, length = 36)
    private String uid;

    @Column(name = "customer_uid")
    private String customerUid;

    @Column(name = "asset_name")
    private String assetName;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_side")
    private OrderSide orderSide;

    @Column(name = "size")
    private BigDecimal size;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        if (uid == null) {
            uid = UUID.randomUUID().toString();
        }
        if (createDate == null) {
            createDate = LocalDateTime.now();
        }
    }

    // İlişkiyi sağlamak için Customer entity'si ile bağlantı (opsiyonel)
    /*
    @ManyToOne
    @JoinColumn(name = "customer_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private Customer customer;
    */
}
