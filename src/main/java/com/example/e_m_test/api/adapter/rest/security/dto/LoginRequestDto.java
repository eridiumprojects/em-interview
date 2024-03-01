package com.example.e_m_test.api.adapter.rest.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequestDto {
    @NotNull
    @Size(message = "Username must be greater than 4 characters and lower than 12 characters!", min = 4, max = 12)
    String username;
    @NotNull
    @Size(message = "Password must be greater than 8 characters!", min = 8)
    String password;
    @JsonIgnore
    UUID deviceToken;
}