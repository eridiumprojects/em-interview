package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.DeleteClientEmailInBound;
import com.example.e_m_test.api.app.api.client.EmailNotFoundException;
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
public class DeleteClientEmailUseCase implements DeleteClientEmailInBound {
    private final EmailRepository emailRepository;
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public Client delete(Long id, Email email) {
        log.info("Client wants to delete his email: {}", email.getAddress());
        Client client = clientRepository.getById(id);
        int valueOfClientEmails = client.getEmails().size();

        if (valueOfClientEmails > 1) {
            Email emailToDelete = client.getEmails().stream()
                    .filter(e -> e.getAddress().equals(email.getAddress()))
                    .findFirst()
                    .orElseThrow(() -> new EmailNotFoundException("Email not found: " + email.getAddress()));
            client.getEmails().remove(emailToDelete);
            emailRepository.deleteByAddress(email.getAddress());
            return clientRepository.save(client);
        }
        return client;
    }
}