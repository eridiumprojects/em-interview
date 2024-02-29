package com.example.e_m_test.api.adapter.rest.wallet.dto;

import com.example.e_m_test.api.domain.wallet.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletDtoMapper {
    WalletInfoDto mapToDto(Wallet wallet);
}
