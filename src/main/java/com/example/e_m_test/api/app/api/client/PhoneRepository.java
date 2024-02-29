package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Phone;

public interface PhoneRepository {
    Phone findByNumber(String number);

    boolean existsByNumber(String number);

    Phone save(Phone phone);

    void deleteByNumber(String number);

}
