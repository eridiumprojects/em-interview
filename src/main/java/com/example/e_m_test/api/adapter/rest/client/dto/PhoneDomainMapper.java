package com.example.e_m_test.api.adapter.rest.client.dto;

import com.example.e_m_test.api.domain.client.Phone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneDomainMapper {
    Phone mapToDomain(AddClientPhoneRequestDto source);

    Phone mapToDomain(UpdateClientPhoneRequestDto source);

    Phone mapToDomain(DeleteClientPhoneRequestDto source);
}
