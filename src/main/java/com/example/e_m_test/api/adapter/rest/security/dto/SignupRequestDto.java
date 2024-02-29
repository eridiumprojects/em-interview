package com.example.e_m_test.api.adapter.rest.security.dto;

import com.example.e_m_test.api.domain.client.Email;
import com.example.e_m_test.api.domain.client.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequestDto {
    String firstName;
    String lastName;
    String patronymic;
    @NotBlank
    String username;
    @NotBlank
    String password;
    @PositiveOrZero
    Long initialBalance;
    List<Phone> phones;
    List<Email> emails;
    @NotBlank
    String birth;
}