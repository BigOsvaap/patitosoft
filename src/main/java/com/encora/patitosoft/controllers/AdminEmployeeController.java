package com.encora.patitosoft.controllers;

import com.encora.patitosoft.dto.AdminEmployeeInfo;
import com.encora.patitosoft.dto.EmployeePositionSalary;
import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.repositories.projections.AdminEmployeeSearchInfo;
import com.encora.patitosoft.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/employees")
@RequiredArgsConstructor
public class AdminEmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<NormalEmployeeInfo> registerEmployee(@RequestBody NormalEmployeeInfo normalEmployeeInfo) {
        return new ResponseEntity<>(service.saveEmployee(normalEmployeeInfo), HttpStatus.CREATED);
    }

    @PostMapping("/position")
    public ResponseEntity assignPositionAndSalaryToEmployee(@RequestBody EmployeePositionSalary eps) {
        service.assignPositionToEmployee(eps.getCorporateEmail(), eps.getPositionName(), eps.getSalary());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<AdminEmployeeSearchInfo>> normalEmployeeSearch(@RequestParam(defaultValue = "") String firstName,
                                                                              @RequestParam(defaultValue = "") String lastName,
                                                                              @RequestParam(defaultValue = "") String position,
                                                                              @RequestParam(defaultValue = "false") Boolean isDeleted,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(service.adminEmployeeSearchInfo(firstName, lastName, position, isDeleted, page, size));
    }

    @GetMapping("/{corporateEmail}")
    public ResponseEntity<AdminEmployeeInfo> detailsSpecificEmployee(@PathVariable String corporateEmail) {
        return ResponseEntity.ok(service.employeeAdminInfoByCorporateEmail(corporateEmail));
    }

    @DeleteMapping("/{corporateEmail}")
    public ResponseEntity softDeleteEmployee(@PathVariable String corporateEmail) {
        service.softDeleteEmployeeByCorporateEmail(corporateEmail);
        return ResponseEntity.ok().build();
    }

}
