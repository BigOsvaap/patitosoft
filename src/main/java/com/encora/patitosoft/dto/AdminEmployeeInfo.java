package com.encora.patitosoft.dto;

import com.encora.patitosoft.repositories.projections.EmployeePositionsHistory;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AdminEmployeeInfo {

    String corporateEmail;
    String firstName;
    String lastName;
    String gender;
    String personalEmail;
    String phoneNumber;
    LocalDate birthDay;
    AddressInfo address;
    List<EmployeePositionsHistory> history;

}
