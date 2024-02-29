package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.app.api.wallet.WalletNotFoundException;
import com.example.e_m_test.api.app.api.wallet.WalletRepository;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WalletRepositoryImpl implements WalletRepository {
    private final WalletJpaRepository walletJpaRepository;

    @Override
    public Wallet findByClient(Client client) {
        return walletJpaRepository.findByClient(client)
                .orElseThrow(() -> new WalletNotFoundException(client.toString()));
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletJpaRepository.save(wallet);
    }

    @Override
    public List<Wallet> findAll() {
        return walletJpaRepository.findAll();
    }

    @Override
    public Wallet findByClientUsername(String username) {
        return walletJpaRepository.findByClientUsername(username)
                .orElseThrow(() -> new WalletNotFoundException(username));
    }

    @Override
    public Wallet findByClientId(Long id) {
        return walletJpaRepository.findByClientId(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
    }
}
