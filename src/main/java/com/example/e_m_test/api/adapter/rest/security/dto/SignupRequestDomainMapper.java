package com.example.e_m_test.api.adapter.rest.security.dto;

import com.example.e_m_test.api.domain.client.Client;
import org.springframework.stereotype.Component;

@Component
public interface SignupRequestDomainMapper {
    Client mapToDomain(SignupRequestDto source);

}