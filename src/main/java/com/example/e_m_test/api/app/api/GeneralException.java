package com.example.e_m_test.api.app.api;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }

    public GeneralException() {
        super();
    }
}