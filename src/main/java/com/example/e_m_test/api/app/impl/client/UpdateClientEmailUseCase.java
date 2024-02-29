package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.adapter.rest.client.dto.UpdateClientEmailRequestDto;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.EmailRepository;
import com.example.e_m_test.api.app.api.client.EmailValidationException;
import com.example.e_m_test.api.app.api.client.UpdateClientEmailInBound;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateClientEmailUseCase implements UpdateClientEmailInBound {
    private final ClientRepository clientRepository;
    private final EmailRepository emailRepository;

    @Override
    public Client update(Long id, UpdateClientEmailRequestDto email) {
        Client client = clientRepository.getById(id);
        Email target = emailRepository.findByAddress(email.getOldEmail());

        if (emailRepository.existsByAddress(email.getNewEmail())) {
            throw new EmailValidationException(email.getNewEmail());
        }
        target.setAddress(email.getNewEmail());

        emailRepository.save(target);

        return clientRepository.save(client);
    }
}
