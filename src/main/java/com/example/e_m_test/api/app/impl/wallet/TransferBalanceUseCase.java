package com.example.e_m_test.api.app.impl.wallet;

import com.example.e_m_test.api.adapter.rest.wallet.dto.TransferRequestDto;
import com.example.e_m_test.api.app.api.ObjectAlreadyExistException;
import com.example.e_m_test.api.app.api.wallet.TransferBalanceInBound;
import com.example.e_m_test.api.app.api.wallet.WalletRepository;
import com.example.e_m_test.api.domain.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferBalanceUseCase implements TransferBalanceInBound {
    private final WalletRepository walletRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Wallet transfer(Long clientId, TransferRequestDto wallet) {
        log.info("Client with id {} wants to do transfer to another client", clientId);

        Wallet senderWallet = walletRepository.findByClientId(clientId);
        if (senderWallet.getCurrentBalance() < wallet.getAmount()) {
            log.error("Transfer error: Client with id: {} need more balance", clientId);
            throw new ArithmeticException("Client sender must have more balance than he want to transfer");
        }

        if (wallet.getRecipientUsername().equals(senderWallet.getClient().getUsername())) {
            throw new ObjectAlreadyExistException("You can not transfer balance to yourself");
        }

        Wallet recipientWallet = walletRepository.findByClientUsername(wallet.getRecipientUsername());
        recipientWallet.setCurrentBalance(recipientWallet.getCurrentBalance() + wallet.getAmount());
        senderWallet.setCurrentBalance(senderWallet.getCurrentBalance() - wallet.getAmount());
        walletRepository.save(recipientWallet);
        return walletRepository.save(senderWallet);
    }
}