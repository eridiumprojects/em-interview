package com.example.e_m_test.api.app.api.wallet;

public class WalletValidationException extends RuntimeException {
    private static final String ERROR_ADD = "Invalid wallet input";

    public WalletValidationException() {
        super(ERROR_ADD);
    }
}
