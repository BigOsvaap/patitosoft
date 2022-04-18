package com.encora.patitosoft.services;

import com.encora.patitosoft.repositories.PositionRepository;
import com.encora.patitosoft.repositories.projections.EmployeeBetweenRange;
import com.encora.patitosoft.repositories.projections.EmployeesByPosition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<EmployeesByPosition> employeesByPositions() {
        return positionRepository.countEmployeesByPosition();
    }

    public List<EmployeeBetweenRange> employeeBetweenRanges(Integer ranges) {
        List<EmployeeBetweenRange> employeeBetween = new ArrayList<>(ranges);
        Double minSalary = positionRepository.currentMinSalary();
        Double maxSalary = positionRepository.currentMaxSalary() + 10000;
        double increment = (maxSalary - minSalary) / ranges;

        for (int i = 0; i < ranges; i++) {
            EmployeeBetweenRange employeesBetweenSalaryRange = positionRepository.getEmployeesBetweenSalaryRange(minSalary, minSalary + increment);
            if (Objects.isNull(employeesBetweenSalaryRange)) {
                Double finalMinSalary = minSalary;
                employeeBetween.add(new EmployeeBetweenRange() {
                    @Override
                    public Double getGreaterThanOrEqual() {
                        return finalMinSalary;
                    }

                    @Override
                    public Double getSmallerThan() {
                        return finalMinSalary + increment;
                    }

                    @Override
                    public Integer getTotalEmployeesBetweenRange() {
                        return 0;
                    }
                });
            } else {
                employeeBetween.add(employeesBetweenSalaryRange);
            }
            minSalary += increment;
        }

        return employeeBetween;
    }

}
