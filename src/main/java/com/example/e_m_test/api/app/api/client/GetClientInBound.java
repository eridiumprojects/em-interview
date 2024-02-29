package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;

public interface GetClientInBound {
    Client get(Long clientId);
}