package com.brokenFirmChallenge.service;

import com.brokenFirmChallenge.model.dto.EmployeeDTO;
import com.brokenFirmChallenge.model.dto.BaseResponse;

import java.util.List;

public interface EmployeeService {
    BaseResponse<EmployeeDTO> login(String email, String password);
    BaseResponse<Void> createEmployee(EmployeeDTO employeeDTO);
    BaseResponse<Void> updateEmployee(String uid, EmployeeDTO employeeDTO);
    BaseResponse<Void> deleteEmployee(String uid);
    BaseResponse<List<EmployeeDTO>> getAllEmployees();
}
