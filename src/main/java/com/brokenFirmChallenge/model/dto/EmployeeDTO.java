package com.brokenFirmChallenge.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private String uid;
    private String name;
    private String email;
    private String password;
}
