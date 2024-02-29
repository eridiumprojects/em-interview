package com.example.e_m_test.api.adapter.rest.security.dto;

import com.example.e_m_test.api.app.api.security.JwtResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JwtResponseDtoMapper {
    JwtResponseDto mapToDto(JwtResponse source);
}