package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;

import java.util.List;

public interface EmailRepository {
    Email findByAddress(String address);
    Email findByClient(Client client);
    boolean existsByAddress(String address);
    boolean existsByClient(Client client);
    Email save(Email email);
    void deleteByAddress(String address);
}
