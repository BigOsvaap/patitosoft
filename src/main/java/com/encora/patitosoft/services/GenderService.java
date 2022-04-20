package com.encora.patitosoft.services;

import com.encora.patitosoft.repositories.GenderRepository;
import com.encora.patitosoft.repositories.projections.EmployeesByGender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderService {

    private final GenderRepository genderRepository;

    public List<EmployeesByGender> employeesByGenders() {
        return genderRepository.countEmployeesByGender();
    }

}
