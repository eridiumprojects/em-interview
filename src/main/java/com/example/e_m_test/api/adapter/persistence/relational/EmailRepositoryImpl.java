package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.app.api.client.EmailNotFoundException;
import com.example.e_m_test.api.app.api.client.EmailRepository;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailRepositoryImpl implements EmailRepository {
    private final EmailJpaRepository emailJpaRepository;

    @Override
    public Email findByAddress(String address) {
        return emailJpaRepository.findByAddress(address)
                .orElseThrow(() -> new EmailNotFoundException(address));
    }

    @Override
    public Email findByClient(Client client) {
        return emailJpaRepository.findByClient(client)
                .orElseThrow(() -> new EmailNotFoundException(client));
    }

    @Override
    public boolean existsByAddress(String address) {
        return emailJpaRepository.existsByAddress(address);
    }

    @Override
    public boolean existsByClient(Client client) {
        return emailJpaRepository.existsByClient(client);
    }

    @Override
    public Email save(Email email) {
        return emailJpaRepository.save(email);
    }

    @Override
    public void deleteByAddress(String address) {
        emailJpaRepository.deleteByAddress(address);
    }
}
