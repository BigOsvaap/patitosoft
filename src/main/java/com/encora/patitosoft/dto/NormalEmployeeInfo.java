package com.encora.patitosoft.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class NormalEmployeeInfo {

    @Email
    String corporateEmail;

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String gender;


    String personalEmail;

    String phoneNumber;

    LocalDate birthDay;

    @NotNull
    AddressInfo address;

}
