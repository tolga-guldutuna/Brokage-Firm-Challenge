package com.brokenFirmChallenge.controller;

import com.brokenFirmChallenge.model.dto.EmployeeDTO;
import com.brokenFirmChallenge.model.dto.BaseResponse;
import com.brokenFirmChallenge.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Admin login for employees")
    @PostMapping("/login")
    public BaseResponse<EmployeeDTO> login(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.login(employeeDTO.getEmail(), employeeDTO.getPassword());
    }

    @Operation(summary = "Create a new employee (Admin)")
    @PostMapping("/create")
    public BaseResponse<Void> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @Operation(summary = "Update an existing employee (Admin)")
    @PutMapping("/update/{uid}")
    public BaseResponse<Void> updateEmployee(@PathVariable String uid, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(uid, employeeDTO);
    }

    @Operation(summary = "Delete an existing employee (Admin)")
    @DeleteMapping("/delete/{uid}")
    public BaseResponse<Void> deleteEmployee(@PathVariable String uid) {
        return employeeService.deleteEmployee(uid);
    }

    @GetMapping("/all")
    public BaseResponse<List<EmployeeDTO>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
