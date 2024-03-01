package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.app.api.ObjectNotFoundException;

public class PhoneNotFoundException extends ObjectNotFoundException {
    private static final String ERROR_MESSAGE_PHONE = "Phone with number %s was not found";

    public PhoneNotFoundException(String number) {
        super(ERROR_MESSAGE_PHONE.formatted(number));
    }
}