package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Phone;

public interface PhoneRepository {
    Phone findByNumber(String number);

    Phone findByClient(Client client);

    boolean existsByNumber(String number);

    boolean existsByClient(Client client);
    Phone save(Phone phone);
    void deleteByNumber(String number);

}
