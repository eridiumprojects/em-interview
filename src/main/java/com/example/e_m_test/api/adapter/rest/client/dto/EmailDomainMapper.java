package com.example.e_m_test.api.adapter.rest.client.dto;

import com.example.e_m_test.api.domain.client.Email;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailDomainMapper {
    Email mapToDomain(AddClientEmailRequestDto source);

    Email mapToDomain(DeleteClientEmailRequestDto source);
}