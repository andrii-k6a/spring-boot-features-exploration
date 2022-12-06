package org.kook.spring.boot.exploration.controller;

import lombok.extern.slf4j.Slf4j;
import org.kook.spring.boot.exploration.exception.EmployeeNotFoundException;
import org.kook.spring.boot.exploration.exception.IllegalOrderStatusException;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String globalHandler(Throwable e) {
        log.error("Unexpected exception", e);
        return "Something went wrong...";
    }

    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String employeeNotFoundHandler(EmployeeNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(IllegalOrderStatusException.class)
    public ResponseEntity<?> illegalOrderStatusHandler(IllegalOrderStatusException e) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You can't complete or cancel an order that is in the " + e.getMessage() + " status"));
    }
}
