package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Phone;

public interface AddClientPhoneInBound {
    Client add(Long id, Phone phone);
}
