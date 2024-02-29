package com.example.e_m_test.api.app.api.security;

import com.example.e_m_test.api.domain.security.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findByToken(String token);

    void deleteById(Long id);

    RefreshToken save(RefreshToken refreshToken);
}
