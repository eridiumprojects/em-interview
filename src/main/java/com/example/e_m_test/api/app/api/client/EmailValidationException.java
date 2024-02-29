package com.example.e_m_test.api.app.api.client;

public class EmailValidationException extends RuntimeException {
    private static final String ERROR_UPDATE = "Invalid update request for email %d";
    private static final String ERROR_ADD = "Invalid add email request";

    public EmailValidationException(String email) {
        super(ERROR_UPDATE.formatted(email));
    }

    public EmailValidationException() {
        super(ERROR_ADD);
    }
}
