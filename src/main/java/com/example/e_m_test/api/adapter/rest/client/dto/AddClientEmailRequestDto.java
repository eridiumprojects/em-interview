package com.example.e_m_test.api.adapter.rest.client.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddClientEmailRequestDto {
    String address;
}
