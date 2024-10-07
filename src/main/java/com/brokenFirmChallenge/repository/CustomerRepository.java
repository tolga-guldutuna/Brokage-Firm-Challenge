package com.brokenFirmChallenge.repository;

import com.brokenFirmChallenge.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUid(String uid);
    Customer findByEmail(String email);
}
