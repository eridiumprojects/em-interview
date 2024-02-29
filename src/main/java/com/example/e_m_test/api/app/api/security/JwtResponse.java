package com.example.e_m_test.api.app.api.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
}