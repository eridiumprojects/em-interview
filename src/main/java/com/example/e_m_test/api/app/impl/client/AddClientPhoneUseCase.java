package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.client.AddClientPhoneInBound;
import com.example.e_m_test.api.app.api.client.ClientNotFoundException;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.PhoneRepository;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Phone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddClientPhoneUseCase implements AddClientPhoneInBound {
    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    @Transactional
    @Override
    public Client add(Long id, Phone phone) {
        log.info("Client with id: {} wants to add new number: {}", id, phone.getNumber());
        if (phoneRepository.existsByNumber(phone.getNumber())) {
            log.error("Client with id: {} tried to add existed number. Rollback", id);
            throw new ObjectAlreadyExistException("Phone number which client want to add, is busy by another client");
        }
        Client client = clientRepository.getById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        Phone newPhone = new Phone();
        newPhone.setNumber(phone.getNumber());
        newPhone.setClient(client);

        client.getPhones().add(newPhone);
        phoneRepository.save(newPhone);
        log.info("Client has been added new phone number successfully");
        return clientRepository.save(client);
    }
}