package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;

import java.util.Optional;

public interface ClientRepository {
    Optional<Client> getByUsername(String username);

    Optional<Client> getById(Long id);

    boolean existsByUsername(String username);

    Client save(Client client);
}