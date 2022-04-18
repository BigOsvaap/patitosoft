package com.encora.patitosoft.controllers;

import com.encora.patitosoft.repositories.projections.EmployeeBetweenRange;
import com.encora.patitosoft.repositories.projections.EmployeeByState;
import com.encora.patitosoft.repositories.projections.EmployeesByGender;
import com.encora.patitosoft.repositories.projections.EmployeesByPosition;
import com.encora.patitosoft.services.CountryService;
import com.encora.patitosoft.services.GenderService;
import com.encora.patitosoft.services.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/reports")
public class ReportController {

    private final PositionService positionService;
    private final GenderService genderService;
    private final CountryService countryService;

    public ReportController(PositionService positionService, GenderService genderService, CountryService countryService) {
        this.positionService = positionService;
        this.genderService = genderService;
        this.countryService = countryService;
    }

    @GetMapping("/positions")
    public ResponseEntity<List<EmployeesByPosition>> positionsReport() {
        return ResponseEntity.ok(positionService.employeesByPositions());
    }

    @GetMapping("/genders")
    public ResponseEntity<List<EmployeesByGender>> gendersReport() {
        return ResponseEntity.ok(genderService.employeesByGenders());
    }

    @GetMapping("/salaries")
    public ResponseEntity<List<EmployeeBetweenRange>> salariesReport(@RequestParam(value = "ranges", defaultValue = "5") Integer ranges) {
        return ResponseEntity.ok(positionService.employeeBetweenRanges(ranges));
    }

    @GetMapping("/countries")
    public ResponseEntity<List<EmployeeByState>> countriesReport(@RequestParam(value = "code", defaultValue = "MX") String code) {
        return ResponseEntity.ok(countryService.employeeByCountryState(code));
    }


}
