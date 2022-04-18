package com.encora.patitosoft.dto;

import com.encora.patitosoft.repositories.projections.EmployeePositionsHistory;

import java.time.LocalDateTime;
import java.util.List;

public class AdminEmployeeInfo {

    String corporateEmail;
    String firstName;
    String lastName;
    String gender;
    String personalEmail;
    String phoneNumber;
    LocalDateTime birthDay;
    AddressInfo address;
    List<EmployeePositionsHistory> history;

    public AdminEmployeeInfo() {
    }

    public String getCorporateEmail() {
        return corporateEmail;
    }

    public void setCorporateEmail(String corporateEmail) {
        this.corporateEmail = corporateEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }

    public AddressInfo getAddress() {
        return address;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

    public List<EmployeePositionsHistory> getHistory() {
        return history;
    }

    public void setHistory(List<EmployeePositionsHistory> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "AdminEmployeeInfo{" +
                "corporateEmail='" + corporateEmail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDay=" + birthDay +
                ", address=" + address +
                ", history=" + history +
                '}';
    }
}
