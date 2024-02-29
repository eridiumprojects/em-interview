package com.example.e_m_test.api.adapter.rest.client.dto;

import com.example.e_m_test.api.domain.client.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientDtoMapper {
    ClientInfoDto mapToDto(Client source);

    List<ClientInfoDto> mapToDto(List<Client> source);
}