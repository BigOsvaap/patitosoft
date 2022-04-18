package com.encora.patitosoft.services;

import com.encora.patitosoft.repositories.CountryRepository;
import com.encora.patitosoft.repositories.projections.EmployeeByState;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<EmployeeByState> employeeByCountryState(String countryCode) {
        return countryRepository.countEmployeeByCountryStates(countryCode);
    }

}
