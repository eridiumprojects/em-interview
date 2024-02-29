package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.client.AddClientEmailInBound;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.EmailRepository;
import com.example.e_m_test.api.app.api.client.EmailValidationException;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddClientEmailUseCase implements AddClientEmailInBound {
    private final ClientRepository clientRepository;
    private final EmailRepository emailRepository;

    @Override
    public Client add(Long id, Email email) {
        if (emailRepository.existsByAddress(email.getAddress())) {
            throw new EmailValidationException();
        }
        Client client = clientRepository.getById(id);

        Email newEmail = new Email();
        newEmail.setAddress(email.getAddress());
        newEmail.setClient(client);

        client.getEmails().add(newEmail);
        emailRepository.save(newEmail);

        return clientRepository.save(client);
    }
}
