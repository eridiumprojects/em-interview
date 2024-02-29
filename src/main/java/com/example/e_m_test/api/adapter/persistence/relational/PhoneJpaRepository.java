package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneJpaRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findByNumber(String number);

    Optional<Phone> findByClient(Client client);

    Boolean existsByNumber(String address);

    Boolean existsByClient(Client client);

    void deleteByNumber(String number);
}
