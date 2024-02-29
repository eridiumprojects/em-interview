package com.example.e_m_test.api.app.impl.security;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AuthException extends RuntimeException {
    private Long timestamp;
    private String errorMessage;

    public AuthException(String message) {
        super(message);
        this.timestamp = Instant.now().toEpochMilli();
    }
}
