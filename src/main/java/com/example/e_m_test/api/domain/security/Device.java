package com.example.e_m_test.api.domain.security;

import com.example.e_m_test.api.domain.client.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "device")
public class Device {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID deviceToken;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client;

    @Column(name = "last_login")
    private Instant lastLogin;
    @Column(nullable = false)
    private Instant created;

    @PrePersist
    public void prePersist() {
        created = Instant.now();
        lastLogin = created;
    }

    @PreUpdate
    public void preUpdate() {
        lastLogin = Instant.now();
    }

    public static Device createDevice(UUID token, Client client) {
        Device device = new Device();
        device.deviceToken = token;
        device.client = client;
        return device;
    }
}