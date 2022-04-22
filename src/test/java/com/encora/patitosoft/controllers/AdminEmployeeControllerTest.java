package com.encora.patitosoft.controllers;

import com.encora.patitosoft.dto.EmployeePositionSalary;
import com.encora.patitosoft.dto.NormalEmployeeInfo;
import com.encora.patitosoft.exceptions.InvalidInputException;
import com.encora.patitosoft.exceptions.NotFoundException;
import com.encora.patitosoft.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.encora.patitosoft.Utils.createAdminEmployeeInfo;
import static com.encora.patitosoft.Utils.createNormalEmployeeInfo;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(AdminEmployeeController.class)
class AdminEmployeeControllerTest {

    @Autowired private WebTestClient webClient;
    @MockBean private EmployeeService service;

    private final String uri = "/v1/admin/employees/";

    @Test
    public void registerEmployeeSuccessfully() {
        NormalEmployeeInfo employee = createNormalEmployeeInfo();

        Mockito.when(service.saveEmployee(employee)).thenReturn(employee);

        webClient.post()
                .uri(uri)
                .body(Mono.just(employee), NormalEmployeeInfo.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void registerEmployeeWithMissingRequiredValues() {
        NormalEmployeeInfo employee = createNormalEmployeeInfo();
        employee.setCorporateEmail(null);

        webClient.post()
                .uri(uri)
                .body(Mono.just(employee), NormalEmployeeInfo.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void registerEmployeeThatAlreadyExists() {
        NormalEmployeeInfo employee =  createNormalEmployeeInfo();

        Mockito.when(service.saveEmployee(employee)).thenThrow(new InvalidInputException("Employee already exists"));

        webClient.post()
                .uri(uri)
                .body(Mono.just(employee), NormalEmployeeInfo.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void assignPositionAndSalaryToEmployeeSuccessfully() {
        EmployeePositionSalary eps = new EmployeePositionSalary();
        eps.setCorporateEmail("oswaldo.vazquez@encora.com");
        eps.setPositionName("Software Engineer");
        eps.setSalary(50000.0);

        webClient.post()
                .uri(uri + "position")
                .body(Mono.just(eps), EmployeePositionSalary.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void assignPositionAndSalaryToEmployeeWithMissingFields() {
        EmployeePositionSalary eps = new EmployeePositionSalary();

        webClient.post()
                .uri(uri + "position")
                .body(Mono.just(eps), EmployeePositionSalary.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void detailsSpecificEmployee() {
        String corporateEmail = "oswaldo.vazquez@encora.com";

        Mockito.when(service.employeeAdminInfoByCorporateEmail(corporateEmail)).thenReturn(createAdminEmployeeInfo());

        webClient.get()
                .uri(uri + corporateEmail)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void detailsSpecificEmployeeWithWrongEmailFormat() {
        String corporateEmail = "oswaldo.vazquez";

        webClient.get()
                .uri(uri + corporateEmail)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();
    }

    @Test
    public void detailsSpecificEmployeeWhenEmailDoesntExists() {
        String corporateEmail = "emailnotfound@patitosoft.com";
        String errorMessage = "Employee not found";
        Mockito.when(service.employeeAdminInfoByCorporateEmail(corporateEmail)).thenThrow(new NotFoundException(errorMessage));

        webClient.get()
                .uri(uri + corporateEmail)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    public void softDeleteEmployeeSuccessfully() {
        String corporateEmail = "oswaldo.vazquez@patitosoft.com";

        webClient.delete()
                .uri(uri + corporateEmail)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void softDeleteEmployeeWithEmptyEmail() {
        String corporateEmail = "";

        webClient.delete()
                .uri(uri + corporateEmail)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    public void softDeleteEmployeeWithWrongEmailFormat() {
        String corporateEmail = "ossdasa";

        webClient.delete()
                .uri(uri + corporateEmail)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }

}