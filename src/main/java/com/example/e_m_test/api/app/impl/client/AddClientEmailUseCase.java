package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.client.AddClientEmailInBound;
import com.example.e_m_test.api.app.api.client.ClientNotFoundException;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.EmailRepository;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddClientEmailUseCase implements AddClientEmailInBound {
    private final ClientRepository clientRepository;
    private final EmailRepository emailRepository;

    @Transactional
    @Override
    public Client add(Long id, Email email) {
        log.info("Client with id: {} wants to add new email: {}", id, email.getAddress());
        if (emailRepository.existsByAddress(email.getAddress())) {
            log.error("Client with id: {} tried to add existed email. Rollback", id);
            throw new ObjectAlreadyExistException("Email which client want to add, is busy by another client");
        }
        Client client = clientRepository.getById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        Email newEmail = new Email();
        newEmail.setAddress(email.getAddress());
        newEmail.setClient(client);

        client.getEmails().add(newEmail);
        emailRepository.save(newEmail);
        log.info("Client has been added new email successfully");

        return clientRepository.save(client);
    }
}