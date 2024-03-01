package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.domain.client.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneJpaRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findByNumber(String number);

    Boolean existsByNumber(String phone);

    Boolean existsByNumberAndClientId(String number, Long clientId);

    void deleteByNumber(String number);
}
