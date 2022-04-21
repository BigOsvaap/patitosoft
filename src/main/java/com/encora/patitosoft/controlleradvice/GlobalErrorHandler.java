package com.encora.patitosoft.controlleradvice;

import com.encora.patitosoft.exceptions.InvalidInputException;
import com.encora.patitosoft.exceptions.NotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public HttpErrorInfo handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
        return createHttpErrorInfo(NOT_FOUND, request, ex);
    }

    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler({PSQLException.class, InvalidInputException.class})
    public HttpErrorInfo handlePSQLException(HttpServletRequest request, Exception ex) {
        return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ValidationErrorResponse handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        return createValidationErrorResponseForConstraintViolation(BAD_REQUEST, request, ex);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponse handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex){
        return createValidationErrorResponseForMethodArgument(BAD_REQUEST, request, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, HttpServletRequest request, Exception ex) {
        return new HttpErrorInfo(httpStatus, request.getServletPath(), ex.getMessage());
    }

    private ValidationErrorResponse createValidationErrorResponseForMethodArgument(HttpStatus httpStatus, HttpServletRequest request, MethodArgumentNotValidException ex) {
        List<Violation> violations = new ArrayList<>();
        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            violations.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return new ValidationErrorResponse(httpStatus, request.getServletPath(), violations);
    }

    private ValidationErrorResponse createValidationErrorResponseForConstraintViolation(HttpStatus status, HttpServletRequest request, ConstraintViolationException ex) {
        List<Violation> violations = new ArrayList<>();
        for (ConstraintViolation violation: ex.getConstraintViolations()) {
            violations.add(new Violation(violation.getPropertyPath().toString().split("\\.")[1], violation.getMessage()));
        }
        return new ValidationErrorResponse(status, request.getServletPath(), violations);
    }

}
