package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.adapter.rest.client.dto.UpdateClientPhoneRequestDto;
import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.client.*;
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
        Client client = clientRepository.getById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        Phone target = phoneRepository.findByNumber(phone.getOldPhone());

        if (!phoneRepository.existsByNumberAndClientId(phone.getOldPhone(), id)) {
            throw new ObjectAlreadyExistException("Client does not have entered phone number. Please change it and try again");
        }

        if (phoneRepository.existsByNumber(phone.getNewPhone())) {
            log.error("Client tried to update number which exists in system database");
            throw new ObjectAlreadyExistException("Phone number which client want to replace, is busy by another client");
        }
        target.setNumber(phone.getNewPhone());

        phoneRepository.save(target);

        return clientRepository.save(client);
    }
}