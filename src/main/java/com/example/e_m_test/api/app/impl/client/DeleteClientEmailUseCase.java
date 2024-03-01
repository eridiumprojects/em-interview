package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.ObjectNotFoundException;
import com.example.e_m_test.api.app.api.client.*;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteClientEmailUseCase implements DeleteClientEmailInBound {
    private final EmailRepository emailRepository;
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public Client delete(Long id, Email email) {
        log.info("Client wants to delete his email: {}", email.getAddress());
        Client client = clientRepository.getById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        int valueOfClientEmails = client.getEmails().size();

        if (!emailRepository.existsByAddressAndClientId(email.getAddress(), id)) {
            log.error("Client with id {} tried to delete email which does not matches him", id);
            throw new ObjectAlreadyExistException("Email " + email.getAddress() + " does not matches to client");
        }

        if (valueOfClientEmails > 1) {
            Email emailToDelete = client.getEmails().stream()
                    .filter(e -> e.getAddress().equals(email.getAddress()))
                    .findFirst()
                    .orElseThrow(() -> new ObjectNotFoundException("Email not found: " + email.getAddress()));
            client.getEmails().remove(emailToDelete);
            emailRepository.deleteByAddress(email.getAddress());
            return clientRepository.save(client);
        }
        log.warn("Client could not to delete his last email. Rollback...");
        throw new ObjectAlreadyExistException("You can not to delete your first email");
    }
}