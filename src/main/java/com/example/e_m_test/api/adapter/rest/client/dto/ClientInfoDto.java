package com.example.e_m_test.api.adapter.rest.client.dto;

import com.example.e_m_test.api.domain.client.Email;
import com.example.e_m_test.api.domain.client.Phone;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientInfoDto {
    String firstName;
    String lastName;
    @NotNull
    String username;
    @NotNull
    List<Email> emails;
    @NotNull
    List<Phone> phones;
}