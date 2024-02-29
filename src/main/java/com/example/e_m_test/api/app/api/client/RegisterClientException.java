package com.example.e_m_test.api.app.api.client;

public class RegisterClientException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Can't register client. Reason %s";

    public RegisterClientException(String reason) {
        super(ERROR_MESSAGE.formatted(reason));
    }
}
