package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.domain.security.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceJpaRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByDeviceToken(UUID token);
}