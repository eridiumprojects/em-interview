package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.adapter.rest.client.dto.UpdateClientPhoneRequestDto;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.PhoneRepository;
import com.example.e_m_test.api.app.api.client.PhoneValidationException;
import com.example.e_m_test.api.app.api.client.UpdateClientPhoneInBound;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Phone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateClientPhoneUseCase implements UpdateClientPhoneInBound {
    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    @Transactional
    @Override
    public Client update(Long id, UpdateClientPhoneRequestDto phone) {
        log.info("Client with id: {} processed to update his number on {}", id, phone.getNewPhone());
        Client client = clientRepository.getById(id);
        Phone target = phoneRepository.findByNumber(phone.getOldPhone());

        if (phoneRepository.existsByNumber(phone.getNewPhone())) {
            throw new PhoneValidationException(phone.getNewPhone());
        }
        target.setNumber(phone.getNewPhone());

        phoneRepository.save(target);

        return clientRepository.save(client);
    }
}