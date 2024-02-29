package com.example.e_m_test.api.app.api.wallet;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.wallet.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    Wallet findByClientId(Long id);
    Wallet findByClient(Client client);
    Wallet findByClientUsername(String username);

    Wallet findById(Long id);

    boolean existsByClient(Client client);
    Wallet save(Wallet wallet);
    List<Wallet> findAll();
}
