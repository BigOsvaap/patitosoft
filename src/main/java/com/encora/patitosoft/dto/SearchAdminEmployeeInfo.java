package com.encora.patitosoft.dto;

public class SearchAdminEmployeeInfo {

    private String corporateEmail;
    private String firstName;
    private String lastName;
    private String positionName;
    private Boolean isDeleted;

    public SearchAdminEmployeeInfo() {
    }

    public SearchAdminEmployeeInfo(String corporateEmail, String firstName, String lastName, String positionName, Boolean isDeleted) {
        this.corporateEmail = corporateEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.positionName = positionName;
        this.isDeleted = isDeleted;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
