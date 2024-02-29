package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.app.api.security.RefreshTokenRepository;
import com.example.e_m_test.api.domain.security.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return refreshTokenJpaRepository.save(refreshToken);
    }
}
