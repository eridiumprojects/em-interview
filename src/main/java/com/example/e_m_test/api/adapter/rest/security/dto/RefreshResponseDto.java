package com.example.e_m_test.api.adapter.rest.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshResponseDto {
    @NotBlank
    String accessToken;
    @NotBlank
    String refreshToken;
}