package com.example.e_m_test.api.domain.wallet;

import com.example.e_m_test.api.domain.client.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@Entity(name = "wallet")
@Table(name = "wallets")
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "initial_balance")
    private Long initialBalance;
    @Column(name = "current_balance")
    private Long currentBalance;
    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    private Client client;
}