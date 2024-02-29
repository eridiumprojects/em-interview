package com.example.e_m_test.api.app.api.security;

import com.example.e_m_test.api.domain.security.Device;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository {
    Optional<Device> findByDeviceToken(UUID token);

    Optional<Device> findById(Long id);

    Device save(Device device);
}
