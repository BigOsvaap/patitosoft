package com.encora.patitosoft.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeePositionSalary {

    @Email
    @NotBlank
    private String corporateEmail;

    @NotBlank
    private String positionName;

    @NotNull
    private Double salary;

}
