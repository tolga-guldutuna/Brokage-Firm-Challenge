package com.ordermangement.repository;


import com.ordermangement.model.entity.Order;
import com.ordermangement.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerUid(String customerUid);
    List<Order> findByCustomerUidAndCreateDateBetween(String customerUid, LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByCustomerUidAndStatus(String customerUid, OrderStatus status);
    Order findByUid(String uid);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET status = 'CANCELED' WHERE uid = :orderUid AND status = 'PENDING'", nativeQuery = true)
    void cancelPendingOrderNative(@Param("orderUid") String orderUid);
}
