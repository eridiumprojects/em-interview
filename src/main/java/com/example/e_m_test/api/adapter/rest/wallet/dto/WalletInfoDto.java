package com.example.e_m_test.api.adapter.rest.wallet.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WalletInfoDto {
    @NotBlank
    Long initialBalance;
    @NotBlank
    Long currentBalance;
}