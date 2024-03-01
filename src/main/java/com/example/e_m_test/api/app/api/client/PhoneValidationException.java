package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;

public class PhoneValidationException extends ObjectAlreadyExistException {
    private static final String ERROR_ADD = "Invalid phone input";

    public PhoneValidationException() {
        super(ERROR_ADD);
    }
}