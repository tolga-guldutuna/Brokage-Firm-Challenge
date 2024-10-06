package com.ordermangement.repository;

import com.ordermangement.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUid(String uid);
}
