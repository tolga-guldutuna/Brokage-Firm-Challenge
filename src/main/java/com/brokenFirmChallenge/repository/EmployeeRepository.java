package com.brokenFirmChallenge.repository;

import com.brokenFirmChallenge.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
    Employee findByUid(String uid);
}
