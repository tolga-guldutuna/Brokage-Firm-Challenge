package com.ordermangement.repository;

import com.ordermangement.entity.Order;
import com.ordermangement.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerUid(String customerUid);
    List<Order> findByCustomerUidAndCreateDateBetween(String customerUid, LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByCustomerUidAndStatus(String customerUid, OrderStatus status);
    Order findByUid(String uid);
}
