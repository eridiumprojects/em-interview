package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;

public class EmailNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE_EMAIL = "Email with address %s was not found";
    private static final String ERROR_MESSAGE_CLIENT = "Email with client %s was not found";

    public EmailNotFoundException(String address) {
        super(ERROR_MESSAGE_EMAIL.formatted(address));
    }
    public EmailNotFoundException(Client client) {
        super(ERROR_MESSAGE_CLIENT.formatted(client));
    }
}
