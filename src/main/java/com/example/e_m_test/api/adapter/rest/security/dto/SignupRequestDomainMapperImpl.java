package com.example.e_m_test.api.adapter.rest.security.dto;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import com.example.e_m_test.api.domain.client.Phone;
import com.example.e_m_test.api.domain.wallet.Wallet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignupRequestDomainMapperImpl implements SignupRequestDomainMapper {
    @Override
    public Client mapToDomain(SignupRequestDto source) {
        Client client = new Client();
        Wallet wallet = new Wallet();

        List<Email> emails = new ArrayList<>();
        for (Email e : source.getEmails()) {
            Email email = new Email();
            email.setAddress(e.getAddress());
            email.setClient(client);
            emails.add(email);
        }

        List<Phone> phones = new ArrayList<>();
        for (Phone p : source.getPhones()) {
            Phone phone = new Phone();
            phone.setNumber(p.getNumber());
            phone.setClient(client);
            phones.add(phone);
        }
        client.setPhones(phones);
        client.setEmails(emails);

        client.setUsername(source.getUsername());
        client.setPassword(source.getPassword());
        client.setFirstName(source.getFirstName());
        client.setLastName(source.getLastName());
        client.setPatronymic(source.getPatronymic());
        client.setBirth(source.getBirth());
        wallet.setClient(client);
        wallet.setInitialBalance(source.getInitialBalance());
        wallet.setCurrentBalance(source.getInitialBalance());

        if (source.getInitialBalance() < 0) {
            wallet.setInitialBalance(0L);
        }
        client.setWallet(wallet);

        return client;
    }
}