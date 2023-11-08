package com.example.integrate_with_backend_task2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
@ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestExcepion(ApiRequestException e)
    {
        //create payload containing exception details
        // HttpStatus badRequest=new HttpStatus.BAD_REQUEST;
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException= new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException , badRequest);

    }

}
