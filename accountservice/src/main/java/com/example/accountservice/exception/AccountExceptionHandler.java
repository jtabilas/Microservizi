package com.example.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.time.ZonedDateTime;

@ControllerAdvice
public class AccountExceptionHandler {


    // function for the set error response
    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> accountNotFoundHandler (AccountNotFoundException exception, HttpServletRequest req) {
        AccountErrorResponse error = new AccountErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                req.getRequestURI(),
                exception.getMessage());

        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AccountErrorResponse> emailExistsHandler (EmailExistsException exception, HttpServletRequest req) {
        AccountErrorResponse error = new AccountErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.CONFLICT.value(),
                req.getRequestURI(),
                exception.getMessage());

        return  new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
