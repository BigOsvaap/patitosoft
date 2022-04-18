package com.encora.patitosoft;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class PostgresDbTestContainer {

    private static final PostgreSQLContainer container = new PostgreSQLContainer("postgres:12.10");

    static {
        container.start();
    }

    @DynamicPropertySource
    static void modify(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

}
