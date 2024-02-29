package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.app.api.client.ClientNotFoundException;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.domain.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {
    private final ClientJpaRepository clientJpaRepository;

    @Override
    public Client getByUsername(String username) {
        return clientJpaRepository.findByUsername(username)
                .orElseThrow(() -> new ClientNotFoundException(username));
    }

    @Override
    public Client getById(Long id) {
        return clientJpaRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
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
