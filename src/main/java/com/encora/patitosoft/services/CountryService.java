package com.encora.patitosoft.services;

import com.encora.patitosoft.repositories.CountryRepository;
import com.encora.patitosoft.repositories.projections.EmployeeByState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<EmployeeByState> employeeByCountryState(String countryCode) {
        return countryRepository.countEmployeeByCountryStates(countryCode);
    }

}
