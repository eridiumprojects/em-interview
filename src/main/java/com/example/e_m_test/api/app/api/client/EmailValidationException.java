package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;

public class EmailValidationException extends ObjectAlreadyExistException {
    private static final String ERROR_ADD = "Invalid email input";

    public EmailValidationException() {
        super(ERROR_ADD);
    }
}