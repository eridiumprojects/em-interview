package com.example.e_m_test.api.app.api.client;

public class ClientNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Client with id %s was not found";
    private static final String ERROR_MESSAGE_USERNAME = "Client with username %s was not found";

    public ClientNotFoundException(Long userId) {
        super(ERROR_MESSAGE.formatted(userId));
    }

    public ClientNotFoundException(String username) {
        super(ERROR_MESSAGE_USERNAME.formatted(username));
    }
}
