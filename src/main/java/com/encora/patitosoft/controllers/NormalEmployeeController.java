package com.encora.patitosoft.controllers;


import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.repositories.projections.NormalEmployeeSearchInfo;
import com.encora.patitosoft.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/v1/employees")
@RequiredArgsConstructor
@Validated
public class NormalEmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<Page<NormalEmployeeSearchInfo>> normalEmployeeSearch(@RequestParam(defaultValue = "") String firstName,
                                                                               @RequestParam(defaultValue = "") String lastName,
                                                                               @RequestParam(defaultValue = "") String position,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.normalEmployeeSearchInfo(firstName, lastName, position, page, size));
    }

    @GetMapping("/{corporateEmail}")
    public ResponseEntity<NormalEmployeeInfo> detailsSpecificEmployee(@Email @PathVariable String corporateEmail) {
        return ResponseEntity.ok(service.employeeNormalInfoByCorporateEmail(corporateEmail));
    }

    @GetMapping("/birthdaytoday")
    public ResponseEntity<List<String>> whoIsBirthdayToday() {
        return ResponseEntity.ok(service.whoIsBirthdayTodayEmails());
    }

    @GetMapping("/birthdaynextweek")
    public ResponseEntity<List<String>> whoIsBirthdayNextWeek() {
        return ResponseEntity.ok(service.whoIsBirthdayNextWeekEmails());
    }

}
