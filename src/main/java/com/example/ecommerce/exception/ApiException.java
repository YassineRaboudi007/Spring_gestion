package com.example.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;

@AllArgsConstructor
@Data
public class ApiException {
    private String message;
    private HttpStatus httpStatus;
    private Instant instant;
}
