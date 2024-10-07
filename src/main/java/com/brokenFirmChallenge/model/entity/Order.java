package com.brokenFirmChallenge.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.brokenFirmChallenge.model.enums.OrderSide;
import com.brokenFirmChallenge.model.enums.OrderStatus;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", unique = true, nullable = false, length = 36)
    private String uid;

    @Column(name = "customer_uid", nullable = false)
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
}
