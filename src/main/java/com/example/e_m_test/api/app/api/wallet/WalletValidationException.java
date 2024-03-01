package com.example.e_m_test.api.app.api.wallet;

public class WalletValidationException extends RuntimeException {
    private static final String ERROR_ADD = "Invalid add wallet request";

    public WalletValidationException() {
        super(ERROR_ADD);
    }
}
