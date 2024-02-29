package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;

public interface DeleteClientEmailInBound {
    Client delete(Long id, Email email);
}