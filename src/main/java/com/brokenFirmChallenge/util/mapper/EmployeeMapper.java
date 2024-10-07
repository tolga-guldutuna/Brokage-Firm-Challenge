package com.brokenFirmChallenge.util.mapper;

import com.brokenFirmChallenge.model.dto.EmployeeDTO;
import com.brokenFirmChallenge.model.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setUid(employeeDTO.getUid());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        return employee;
    }

    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setUid(employee.getUid());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        return dto;
    }
}
