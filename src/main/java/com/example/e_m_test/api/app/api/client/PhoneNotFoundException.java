package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;

public class PhoneNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE_PHONE = "Phone with number %s was not found";
    private static final String ERROR_MESSAGE_CLIENT = "Phone with client %s was not found";

    public PhoneNotFoundException(String number) {
        super(ERROR_MESSAGE_PHONE.formatted(number));
    }

    public PhoneNotFoundException(Client client) {
        super(ERROR_MESSAGE_CLIENT.formatted(client));
    }
}