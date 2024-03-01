package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.domain.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {
    private final ClientJpaRepository clientJpaRepository;

    @Override
    public Optional<Client> getByUsername(String username) {
        return clientJpaRepository.findByUsername(username);
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientJpaRepository.findById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return clientJpaRepository.existsByUsername(username);
    }

    @Override
    public Client save(Client client) {
        return clientJpaRepository.save(client);
    }
}