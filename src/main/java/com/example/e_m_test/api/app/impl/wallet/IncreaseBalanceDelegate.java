package com.example.e_m_test.api.app.impl.wallet;

import com.example.e_m_test.api.app.api.wallet.WalletRepository;
import com.example.e_m_test.api.domain.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncreaseBalanceDelegate {
    private final WalletRepository walletRepository;

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void increaseWalletBalance() {
        List<Wallet> wallets = walletRepository.findAll();
        for (Wallet wallet : wallets) {
            double newBalance = wallet.getCurrentBalance() * 1.05;
            double maxAllowedBalance = wallet.getInitialBalance() * 2.07;
            if (newBalance > maxAllowedBalance) {
                newBalance = maxAllowedBalance;
            }
            wallet.setCurrentBalance((long) newBalance);
            walletRepository.save(wallet);
        }
    }
}