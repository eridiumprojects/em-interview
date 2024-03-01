package com.example.e_m_test.api.adapter.rest.security.dto;

import com.example.e_m_test.api.domain.client.Email;
import com.example.e_m_test.api.domain.client.Phone;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupRequestDto {
    String firstName;
    String lastName;
    String patronymic;
    @NotNull
    @Size(message = "Username must be greater than 4 characters and lower than 12 characters!", min = 4, max = 12)
    String username;
    @NotNull
    @Size(message = "Password must be greater than 8 characters!", min = 8)
    String password;
    @PositiveOrZero(message = "Initial balance must be greater or equals 0")
    Long initialBalance;
    List<Phone> phones;
    List<Email> emails;
    @NotNull
    @Size(message = "Date of birth must be equals 10 characters", min = 10)
    String birth;
}