package com.example.e_m_test.api.app.api.security;

import com.example.e_m_test.api.domain.security.RefreshToken;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);
}