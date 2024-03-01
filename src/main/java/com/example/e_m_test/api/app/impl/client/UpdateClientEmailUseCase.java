package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.adapter.rest.client.dto.UpdateClientEmailRequestDto;
import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.client.ClientNotFoundException;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.EmailRepository;
import com.example.e_m_test.api.app.api.client.UpdateClientEmailInBound;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateClientEmailUseCase implements UpdateClientEmailInBound {
    private final ClientRepository clientRepository;
    private final EmailRepository emailRepository;

    @Transactional
    @Override
    public Client update(Long id, UpdateClientEmailRequestDto email) {
        log.info("Client with id: {} processed to update his email on {}", id, email.getNewEmail());
        Client client = clientRepository.getById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        Email target = emailRepository.findByAddress(email.getOldEmail());

        if (!emailRepository.existsByAddressAndClientId(email.getOldEmail(), id)) {
            log.error("Client with id: {} does not have email which he input", id);
            throw new ObjectAlreadyExistException("Client does not have entered email. Please change it and try again");
        }

        if (emailRepository.existsByAddress(email.getNewEmail())) {
            log.error("Client tried to update email which exists in system database");
            throw new ObjectAlreadyExistException("Email which client want to replace, is busy by another client");
        }
        target.setAddress(email.getNewEmail());

        emailRepository.save(target);

        return clientRepository.save(client);
    }
}