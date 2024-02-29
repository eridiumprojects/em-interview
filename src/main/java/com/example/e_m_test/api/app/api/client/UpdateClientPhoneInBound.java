package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.adapter.rest.client.dto.UpdateClientPhoneRequestDto;
import com.example.e_m_test.api.domain.client.Client;

public interface UpdateClientPhoneInBound {
    Client update(Long id, UpdateClientPhoneRequestDto phone);
}
