package com.brokenFirmChallenge.service.impl;

import com.brokenFirmChallenge.model.dto.EmployeeDTO;
import com.brokenFirmChallenge.model.entity.Employee;
import com.brokenFirmChallenge.repository.EmployeeRepository;
import com.brokenFirmChallenge.service.EmployeeService;
import com.brokenFirmChallenge.util.mapper.EmployeeMapper;
import com.brokenFirmChallenge.model.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<EmployeeDTO> login(String email, String password) {
        Employee employee = employeeRepository.findByEmail(email);
        if (employee != null && passwordEncoder.matches(password, employee.getPassword())) {
            return new BaseResponse<>(true, "Login successful", EmployeeMapper.toDTO(employee));
        }
        return new BaseResponse<>(false, "Invalid credentials", null);
    }

    @Override
    public BaseResponse<Void> createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.findByEmail(employeeDTO.getEmail()) != null) {
            return new BaseResponse<>(false, "Email already in use", null);
        }
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return new BaseResponse<>(true, "Employee created successfully", null);
    }

    @Override
    public BaseResponse<Void> updateEmployee(String uid, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findByUid(uid);
        if (existingEmployee == null) {
            return new BaseResponse<>(false, "Employee not found", null);
        }
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setEmail(employeeDTO.getEmail());
        if (employeeDTO.getPassword() != null && !employeeDTO.getPassword().isEmpty()) {
            existingEmployee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        }
        employeeRepository.save(existingEmployee);
        return new BaseResponse<>(true, "Employee updated successfully", null);
    }

    @Override
    public BaseResponse<Void> deleteEmployee(String uid) {
        Employee existingEmployee = employeeRepository.findByUid(uid);
        if (existingEmployee == null) {
            return new BaseResponse<>(false, "Employee not found", null);
        }
        employeeRepository.delete(existingEmployee);
        return new BaseResponse<>(true, "Employee deleted successfully", null);
    }

    @Override
    public BaseResponse<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
        return new BaseResponse<>(true, "All employees retrieved successfully", employeeDTOs);
    }


}
