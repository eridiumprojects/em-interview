package com.example.e_m_test.api.adapter.rest.security.dto;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.wallet.Wallet;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
public interface SignupRequestDomainMapper {
    Client mapToDomain(SignupRequestDto source);

}
