package com.example.e_m_test.api.adapter.rest.client.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateClientPhoneRequestDto {
    @NotNull
    @Size(message = "Phone must be greater than 10 characters!", min = 10)
    String oldPhone;
    @NotNull
    @Size(message = "Phone must be greater than 10 characters!", min = 10)
    String newPhone;
}