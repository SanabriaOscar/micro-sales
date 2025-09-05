package com.micro.products.exceptions.handler;

import com.micro.products.exceptions.model.ExceptionGeneral;
import com.micro.products.exceptions.type.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ProductException.class)
    public final ResponseEntity<String>handleUserException(final ProductException userException){
        ExceptionGeneral exceptionGeneral = ExceptionGeneral.builder().build();
        return new ResponseEntity<>(userException.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
