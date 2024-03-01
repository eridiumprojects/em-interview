package com.example.e_m_test.api.adapter.rest.client.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddClientEmailRequestDto {
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9-.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    String address;
}