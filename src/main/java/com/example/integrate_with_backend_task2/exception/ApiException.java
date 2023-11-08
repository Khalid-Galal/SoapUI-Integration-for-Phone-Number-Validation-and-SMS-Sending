package com.example.integrate_with_backend_task2.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ApiException {
    private final String massage;

    private final HttpStatus httpStatus;

    private final ZonedDateTime timestamp;

}
