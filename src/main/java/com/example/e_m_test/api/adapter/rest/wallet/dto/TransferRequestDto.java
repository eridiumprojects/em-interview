package com.example.e_m_test.api.adapter.rest.wallet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferRequestDto {
    @NotNull
    String recipientUsername;
    @PositiveOrZero
    Long amount;
}