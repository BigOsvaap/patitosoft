package com.encora.patitosoft.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NormalEmployeeInfo {

    String corporateEmail;
    String firstName;
    String lastName;
    String gender;
    String personalEmail;
    String phoneNumber;
    LocalDate birthDay;
    AddressInfo address;

}
