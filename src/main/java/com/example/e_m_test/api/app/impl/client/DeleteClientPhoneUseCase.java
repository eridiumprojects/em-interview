package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.ObjectNotFoundException;
import com.example.e_m_test.api.app.api.client.ClientNotFoundException;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.DeleteClientPhoneInBound;
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
public class DeleteClientPhoneUseCase implements DeleteClientPhoneInBound {
    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    @Transactional
    @Override
    public Client delete(Long id, Phone phone) {
        log.info("Client wants to delete his number: {}", phone.getNumber());
        Client client = clientRepository.getById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        int valueOfClientPhones = client.getPhones().size();

        if (!phoneRepository.existsByNumberAndClientId(phone.getNumber(), id)) {
            log.error("Client with id {} tried to delete phone number which does not matches him", id);
            throw new ObjectAlreadyExistException("Phone " + phone.getNumber() + " does not matches to client");
        }

        if (valueOfClientPhones > 1) {
            Phone phoneToDelete = client.getPhones().stream()
                    .filter(e -> e.getNumber().equals(phone.getNumber()))
                    .findFirst()
                    .orElseThrow(() -> new ObjectNotFoundException("Phone with number " + phone.getNumber() + " not found"));
            client.getPhones().remove(phoneToDelete);
            phoneRepository.deleteByNumber(phone.getNumber());
            return clientRepository.save(client);
        }
        log.warn("Client could not to delete his last number. Rollback...");
        throw new ObjectAlreadyExistException("You can not to delete your first phone number.");
    }
}