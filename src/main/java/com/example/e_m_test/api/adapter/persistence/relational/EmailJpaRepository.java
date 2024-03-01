package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.domain.client.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailJpaRepository extends JpaRepository<Email, Long> {
    Optional<Email> findByAddress(String address);

    Boolean existsByAddress(String address);

    Boolean existsByAddressAndClientId(String address, Long clientId);

    void deleteByAddress(String address);
}
