package com.example.e_m_test.api.adapter.rest.client.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateClientEmailRequestDto {
    @NotNull
    String oldEmail;
    @NotNull
    String newEmail;
}