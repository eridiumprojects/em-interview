package com.example.e_m_test.api.app.impl.wallet;

import com.example.e_m_test.api.adapter.rest.wallet.dto.TransferRequestDto;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.wallet.TransferBalanceInBound;
import com.example.e_m_test.api.app.api.wallet.WalletRepository;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferBalanceUseCase implements TransferBalanceInBound {
    private final WalletRepository walletRepository;
    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public Wallet transfer(Long clientId, TransferRequestDto wallet) {
        Wallet senderWallet = walletRepository.findByClientId(clientId);
        log.info("step 1 {}", senderWallet);
        if (senderWallet.getCurrentBalance() < wallet.getAmount()) {
            throw new RuntimeException();
        }
        log.info("step 2");
        Wallet recipientWallet = walletRepository.findByClientUsername(wallet.getRecipientUsername());
        log.info("step 3 {}", recipientWallet);
        recipientWallet.setCurrentBalance(recipientWallet.getCurrentBalance() + wallet.getAmount());
        senderWallet.setCurrentBalance(senderWallet.getCurrentBalance() - wallet.getAmount());
        walletRepository.save(recipientWallet);
        return walletRepository.save(senderWallet);
    }
}
