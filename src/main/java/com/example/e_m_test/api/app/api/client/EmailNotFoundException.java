package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.app.api.ObjectNotFoundException;

public class EmailNotFoundException extends ObjectNotFoundException {
    private static final String ERROR_MESSAGE_EMAIL = "Email with address %s was not found";

    public EmailNotFoundException(String address) {
        super(ERROR_MESSAGE_EMAIL.formatted(address));
    }
}