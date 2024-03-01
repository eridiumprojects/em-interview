package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.app.api.security.JwtResponse;

public interface LoginClientInBound {
    JwtResponse login(String username, String password);
}