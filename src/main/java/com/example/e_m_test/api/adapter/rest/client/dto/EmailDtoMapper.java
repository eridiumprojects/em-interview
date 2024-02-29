package com.example.e_m_test.api.adapter.rest.client.dto;

import com.example.e_m_test.api.domain.client.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailDtoMapper {
    ClientInfoDto mapToDto(Client source);
}