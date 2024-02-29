package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    Boolean existsByUsername(String username);
}
