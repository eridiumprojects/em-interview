package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.app.api.security.JwtResponse;

import java.util.UUID;

public interface LoginClientInBound {
    JwtResponse login(String username, String password, UUID deviceToken);
}