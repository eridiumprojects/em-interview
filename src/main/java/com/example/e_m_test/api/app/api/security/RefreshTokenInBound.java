package com.example.e_m_test.api.app.api.security;


public interface RefreshTokenInBound {
    JwtResponse refresh(String refreshToken);
}