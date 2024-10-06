package com.ordermangement.repository;

import com.ordermangement.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUid(String uid);
    Customer findByEmail(String email);
}
