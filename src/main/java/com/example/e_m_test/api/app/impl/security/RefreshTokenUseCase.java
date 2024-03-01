package com.example.e_m_test.api.app.impl.security;

import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.security.AuthErrorMessages;
import com.example.e_m_test.api.app.api.security.JwtResponse;
import com.example.e_m_test.api.app.api.security.RefreshTokenInBound;
import com.example.e_m_test.api.app.api.security.RefreshTokenRepository;
import com.example.e_m_test.api.domain.security.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenUseCase implements RefreshTokenInBound {
    private final JwtService jwtService;
    private final ClientRepository clientRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public JwtResponse refresh(String refreshToken) {
        log.info("Refresh token has been updated");
        if (!jwtService.validateRefreshToken(refreshToken)) {
            throw new AuthException(AuthErrorMessages.INVALID_REFRESH_TOKEN);
        }

        var claims = jwtService.getRefreshClaims(refreshToken);
        var client = clientRepository.getById(Long.parseLong(claims.getUserId()));

        var currentRefreshToken = refreshTokenRepository.findByToken(refreshToken);
        if (currentRefreshToken.isEmpty()) {
            throw new AuthException(AuthErrorMessages.REFRESH_TOKEN_DOESNT_EXISTS);
        }
        refreshTokenRepository.deleteById(currentRefreshToken.get().getId());

        var tokens = jwtService.generateAccessRefreshTokens(client.get().getUsername(), client.get().getId());
        var newRefresh = new RefreshToken();
        newRefresh.setClient(client.get());
        newRefresh.setToken(tokens.getRefreshToken());
        refreshTokenRepository.save(newRefresh);
        return tokens;
    }
}