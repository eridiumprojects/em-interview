package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.adapter.rest.client.dto.UpdateClientEmailRequestDto;
import com.example.e_m_test.api.domain.client.Client;

public interface UpdateClientEmailInBound {
    Client update(Long id, UpdateClientEmailRequestDto email);
}
