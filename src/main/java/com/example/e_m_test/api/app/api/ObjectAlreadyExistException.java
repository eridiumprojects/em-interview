package com.example.e_m_test.api.app.api;

public class ObjectAlreadyExistException extends GeneralException {
    public ObjectAlreadyExistException() {
        super();
    }

    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}