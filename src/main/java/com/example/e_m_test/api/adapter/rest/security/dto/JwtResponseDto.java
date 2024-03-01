package com.example.e_m_test.api.adapter.rest.security.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponseDto {
    @NotNull
    String accessToken;
    @NotNull
    String refreshToken;
}