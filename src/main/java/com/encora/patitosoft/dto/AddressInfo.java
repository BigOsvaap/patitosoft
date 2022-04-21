package com.encora.patitosoft.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddressInfo {

    @NotBlank
    String city;

    @NotBlank
    String street;

    @NotBlank
    String zipCode;

    @Size(min = 2, max = 2)
    String countryCode;

    @NotBlank
    String state;

}
