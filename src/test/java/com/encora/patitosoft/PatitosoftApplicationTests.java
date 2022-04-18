package com.encora.patitosoft;

import com.encora.patitosoft.repositories.GenderRepository;
import com.encora.patitosoft.repositories.PositionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PatitosoftApplicationTests extends PostgresDbTestContainer{

    @Autowired private WebTestClient webClient;
    @Autowired private PositionRepository positionRepository;
    @Autowired private GenderRepository genderRepository;

    @Test
    public void genderReportIncludesAllRegisteredGenders() {
        WebTestClient.BodyContentSpec bodyContentSpec = webClient.get()
                .uri("/v1/reports/genders")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();

        bodyContentSpec.jsonPath("$.length()").isEqualTo(genderRepository.findAll().size());

    }

    @Test
    public void positionReportIncludesAllRegisteredPositions() {
        WebTestClient.BodyContentSpec bodyContentSpec = webClient.get()
                .uri("/v1/reports/positions")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody();

        bodyContentSpec.jsonPath("$.length()").isEqualTo(positionRepository.findAll().size());

    }

}
