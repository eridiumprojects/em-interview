package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;

public interface ClientRepository {
    Client getByUsername(String username);

    Client getById(Long id);

    boolean existsByUsername(String username);

    Client save(Client client);
}
