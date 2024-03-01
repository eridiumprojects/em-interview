package com.example.e_m_test.api.app.impl.security;

import com.example.e_m_test.api.app.api.security.AuthErrorMessages;
import com.example.e_m_test.api.app.api.security.JwtResponse;
import com.example.e_m_test.api.domain.security.JwtAuth;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
public class JwtService {
    public static final String USER_ID_CLAIM = "userId";
    private final String jwtAccessSecret;
    private final String jwtRefreshSecret;
    private final String jwtStorageName;
    private final Duration accessTokenExpiration;
    private final Duration refreshTokenExpiration;
    private final RedissonClient redissonClient;

    public JwtService(
            @Value("${security.jwt.access.secret}") String jwtAccessSecret,
            @Value("${security.jwt.refresh.secret}") String jwtRefreshSecret,
            @Value("${security.jwt.storage}") String jwtStorageName,
            @Value("${security.jwt.access.expiration}") Duration accessTokenExpiration,
            @Value("${security.jwt.refresh.expiration}") Duration refreshTokenExpiration,
            RedissonClient redissonClient) {
        this.jwtAccessSecret = jwtAccessSecret;
        this.jwtRefreshSecret = jwtRefreshSecret;
        this.jwtStorageName = jwtStorageName;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.redissonClient = redissonClient;
    }

    public JwtAuth getJwtAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuth) {
            return ((JwtAuth) authentication);
        } else {
            return JwtAuth.builder().build();
        }
    }

    public JwtResponse generateAccessRefreshTokens(String username, Long userId) {
        RMapCache<Long, String> map = redissonClient.getMapCache(jwtStorageName);
        String newAccessToken = generateAccessToken(username, userId);
        map.put(userId,
                newAccessToken,
                accessTokenExpiration.toMinutes(),
                TimeUnit.MINUTES);

        return new JwtResponse(
                newAccessToken,
                generateRefreshToken(username, userId));
    }

    public String generateAccessToken(String username, Long userId) {
        final Instant accessExpirationInstant =
                Instant.now().plus(accessTokenExpiration);
        final Date accessExpiration = Date.from(accessExpirationInstant);
        return Jwts.builder()
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, jwtAccessSecret)
                .setSubject(username)
                .claim(USER_ID_CLAIM, userId.toString())
                .compact();
    }

    public String generateRefreshToken(String username, Long userId) {
        final Instant refreshExpirationInstant = Instant.now().plus(refreshTokenExpiration);
        final Date refreshExpiration = Date.from(refreshExpirationInstant);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(refreshExpiration)
                .claim(USER_ID_CLAIM, userId.toString())
                .signWith(SignatureAlgorithm.HS512, jwtRefreshSecret)
                .compact();
    }

    public boolean validateAccessToken(@NonNull String token) {
        return validateToken(token, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String token) {
        return validateToken(token, jwtRefreshSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull String secret) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.warn(AuthErrorMessages.TOKEN_EXPIRED, expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error(AuthErrorMessages.UNSUPPORTED_JWT, unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error(AuthErrorMessages.MALFORMED_JWT, mjEx);
        } catch (SignatureException sEx) {
            log.error(AuthErrorMessages.INVALID_SIGNATURE, sEx);
        } catch (Exception e) {
            log.error(AuthErrorMessages.INVALID_TOKEN, e);
        }
        return false;
    }

    public ClaimsHolder getAccessClaims(@NonNull String token) {
        return new ClaimsHolder(getClaims(token, jwtAccessSecret));
    }

    public ClaimsHolder getRefreshClaims(@NonNull String token) {
        return new ClaimsHolder(getClaims(token, jwtRefreshSecret));
    }

    private Claims getClaims(@NonNull String token, @NonNull String secret) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @RequiredArgsConstructor
    public static class ClaimsHolder {
        private final Claims claims;

        public String getUserId() {
            return claims.get(USER_ID_CLAIM, String.class);
        }

        public String getUsername() {
            return claims.getSubject();
        }
    }
}