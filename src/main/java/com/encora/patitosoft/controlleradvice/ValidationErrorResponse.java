package com.encora.patitosoft.controlleradvice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class ValidationErrorResponse {

    private final ZonedDateTime timestamp;
    private final String path;
    private final HttpStatus httpStatus;
    private final List<Violation> violations;

    public ValidationErrorResponse(HttpStatus httpStatus, String path, List<Violation> violations) {
        timestamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.path = path;
        this.violations = violations;
    }

}
