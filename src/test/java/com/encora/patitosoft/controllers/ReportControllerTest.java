package com.encora.patitosoft.controllers;

import com.encora.patitosoft.services.CountryService;
import com.encora.patitosoft.services.GenderService;
import com.encora.patitosoft.services.PositionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired private WebTestClient webClient;
    @MockBean private PositionService positionService;
    @MockBean private GenderService genderService;
    @MockBean private CountryService countryService;

    private final String uri = "/v1/reports/";

    @Test
    public void positionsReport() {
        Mockito.when(positionService.employeesByPositions()).thenReturn(new ArrayList<>());

        webClient.get()
                .uri(uri + "positions")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void gendersReport() {
        Mockito.when(genderService.employeesByGenders()).thenReturn(new ArrayList<>());

        webClient.get()
                .uri(uri + "genders")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void salariesReport() {
        Mockito.when(positionService.employeeBetweenRanges(anyInt())).thenReturn(new ArrayList<>());

        webClient.get()
                .uri(uri + "salaries")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void countriesReport() {
        Mockito.when(countryService.employeeByCountryState(anyString())).thenReturn(new ArrayList<>());

        webClient.get()
                .uri(uri + "countries")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

}