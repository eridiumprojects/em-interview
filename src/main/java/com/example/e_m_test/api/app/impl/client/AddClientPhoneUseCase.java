package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.client.AddClientPhoneInBound;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.PhoneRepository;
import com.example.e_m_test.api.app.api.client.PhoneValidationException;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Phone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddClientPhoneUseCase implements AddClientPhoneInBound {
    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    @Override
    public Client add(Long id, Phone phone) {
        if (phoneRepository.existsByNumber(phone.getNumber())) {
            throw new PhoneValidationException();
        }
        Client client = clientRepository.getById(id);

        Phone newPhone = new Phone();
        newPhone.setNumber(phone.getNumber());
        newPhone.setClient(client);

        client.getPhones().add(newPhone);
        phoneRepository.save(newPhone);

        return clientRepository.save(client);
    }
}