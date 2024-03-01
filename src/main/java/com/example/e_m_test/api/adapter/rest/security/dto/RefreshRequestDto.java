package com.example.e_m_test.api.adapter.rest.security.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshRequestDto {
    @NotNull
    @Size(message = "Refresh token must be greater than 20 characters!", min = 20)
    String refreshToken;
}