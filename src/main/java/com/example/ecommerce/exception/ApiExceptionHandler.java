package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = MyException.class)
    private ResponseEntity<Object> handleException(MyException e){
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_GATEWAY, Instant.now());
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
