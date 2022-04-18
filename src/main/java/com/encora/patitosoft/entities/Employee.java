package com.encora.patitosoft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private UUID id;

    @Column(name = "corporate_email", nullable = false, unique = true)
    private String corporateEmail;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "personal_email", unique = true)
    private String personalEmail;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(name = "is_deleted", nullable = false, insertable = false)
    private Boolean isDeleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    public Employee() {}

    public Employee(String corporateEmail, String firstName, String lastName, String personalEmail, String phoneNumber, String city, String street, String zipCode, LocalDate birthDay, Gender gender, Country country, State state) {
        this.corporateEmail = corporateEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.birthDay = birthDay;
        this.isDeleted = false;
        this.gender = gender;
        this.country = country;
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
