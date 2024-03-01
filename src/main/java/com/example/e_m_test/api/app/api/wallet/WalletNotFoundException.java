package com.example.e_m_test.api.app.api.wallet;

import com.example.e_m_test.api.app.api.ObjectNotFoundException;

public class WalletNotFoundException extends ObjectNotFoundException {
    private static final String ERROR_MESSAGE = "Wallet with id %s was not found";
    private static final String ERROR_MESSAGE_CLIENT = "Wallet with client %s was not found";

    public WalletNotFoundException(Long userId) {
        super(ERROR_MESSAGE.formatted(userId));
    }

    public WalletNotFoundException(String username) {
        super(ERROR_MESSAGE_CLIENT.formatted(username));
    }
}