package com.example.e_m_test.api.app.api.client;

public class PhoneValidationException extends RuntimeException {
    private static final String ERROR_UPDATE = "Invalid update request for phone number %d";
    private static final String ERROR_ADD = "Invalid add phone request";

    public PhoneValidationException(String phone) {
        super(ERROR_UPDATE.formatted(phone));
    }

    public PhoneValidationException() {
        super(ERROR_ADD);
    }
}
