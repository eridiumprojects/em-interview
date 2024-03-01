package com.example.e_m_test.api.adapter.rest.wallet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferRequestDto {
    @NotNull
    @Size(message = "Username must be greater than 4 characters and lower than 12 characters!", min = 4,max = 12)
    String recipientUsername;
    @PositiveOrZero(message = "Balance must be greater or equals 0")
    Long amount;
}