package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.EmailRepository;
import com.example.e_m_test.api.app.api.client.PhoneRepository;
import com.example.e_m_test.api.app.api.client.RegisterClientInBound;
import com.example.e_m_test.api.app.api.wallet.WalletRepository;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import com.example.e_m_test.api.domain.client.Phone;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterClientUseCase implements RegisterClientInBound {
    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;
    private final WalletRepository walletRepository;
    private final EmailRepository emailRepository;
    private final PhoneRepository phoneRepository;

    @Transactional
    @Override
    public Client register(Client client) {
        log.info("Client requested for registration process");
        validateClient(client);
        client.setPassword(encoder.encode(client.getPassword()));
        clientRepository.save(client);
        walletRepository.save(client.getWallet());
        log.info("Client has been successfully registered with id {}", client.getId());
        return client;
    }

    private void validateClient(Client client) {
        log.info("Starting to validate the incoming client");
        List<Email> emails = new ArrayList<>(client.getEmails());
        List<Phone> phones = new ArrayList<>(client.getPhones());

        if (clientRepository.existsByUsername(client.getUsername())) {
            log.error("Client validation error: reason - already taken username");
            throw new ObjectAlreadyExistException("Username " + client.getUsername() + " is already taken");
        }

        for (Email email : emails) {
            if (emailRepository.existsByAddress(email.getAddress())) {
                log.error("Client validation error: reason - already taken email");
                throw new ObjectAlreadyExistException("Email " + email.getAddress() + " is already in use");
            }
        }
        for (Phone phone : phones) {
            if (phoneRepository.existsByNumber(phone.getNumber())) {
                log.error("Client validation error: reason - already taken number");
                throw new ObjectAlreadyExistException("Phone " + phone.getNumber() + "is already taken");
            }
        }
    }
}