package com.gauthier.lab.web.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(HeroNotFoundException.class)
    public ProblemDetail handleHeroNotFoundException(HeroNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle("Hero Not Found");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("code", "HERO_NOT_FOUND");
        return problemDetail;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HeroNameIsAlreadyUseException.class)
    public ProblemDetail handleHeroNameIsAlreadyUseException(HeroNameIsAlreadyUseException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(409);
        problemDetail.setTitle("Hero Name Already In Use");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("code", "HERO_NAME_ALREADY_IN_USE");
        return problemDetail;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(400);
        problemDetail.setTitle("Validation Error");
        problemDetail.setDetail("Validation failed for one or more fields");
        problemDetail.setProperty("code", "VALIDATION_FAILED");
        problemDetail.setProperty("errors",
                ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList());
        return problemDetail;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleValidationException(HttpMessageNotReadableException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(400);
        problemDetail.setTitle("Malformed Request");
        problemDetail.setDetail("The request body is malformed or unreadable");
        problemDetail.setProperty("code", "MALFORMED_REQUEST");
        return problemDetail;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(500);
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setDetail("Internal error");
        problemDetail.setProperty("code", "INTERNAL_ERROR");
        return problemDetail;
    }
}


