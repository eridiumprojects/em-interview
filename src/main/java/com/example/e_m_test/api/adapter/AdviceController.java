package com.example.e_m_test.api.adapter;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.ObjectNotFoundException;
import com.example.e_m_test.api.app.api.client.RegisterClientException;
import com.example.e_m_test.api.app.impl.security.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<Object> handleNotFound(ObjectNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ObjectAlreadyExistException.class})
    public ResponseEntity<Object> handleConflict(ObjectAlreadyExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {AuthException.class})
    public ResponseEntity<Object> handleAuthException(AuthException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RegisterClientException.class})
    public ResponseEntity<Object> handleAuthException(RegisterClientException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ArithmeticException.class})
    public ResponseEntity<Object> handleAuthException(ArithmeticException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}