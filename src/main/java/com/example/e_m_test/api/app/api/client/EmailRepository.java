package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Email;

public interface EmailRepository {
    Email findByAddress(String address);

    boolean existsByAddress(String address);

    Email save(Email email);

    void deleteByAddress(String address);
}
