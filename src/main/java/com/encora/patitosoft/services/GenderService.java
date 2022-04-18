package com.encora.patitosoft.services;

import com.encora.patitosoft.repositories.GenderRepository;
import com.encora.patitosoft.repositories.projections.EmployeesByGender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderService {

    private final GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<EmployeesByGender> employeesByGenders() {
        return genderRepository.countEmployeesByGender();
    }

}
