package com.example.e_m_test.api.app.impl.wallet;

import com.example.e_m_test.api.adapter.rest.wallet.dto.TransferRequestDto;
import com.example.e_m_test.api.app.api.wallet.WalletRepository;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.wallet.Wallet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class TransferBalanceUseCaseTest {
    @Mock
    private WalletRepository walletRepository;
    @InjectMocks
    private TransferBalanceUseCase transferBalanceUseCase;

    @Test
    void testSuccessfulTransfer() {
        Long senderId = 1L;
        Long transferAmount = 100L;

        Client senderClient = new Client();
        senderClient.setId(senderId);
        Wallet senderWallet = new Wallet();
        senderWallet.setCurrentBalance(500L);
        senderWallet.setClient(senderClient);

        Client recipientClient = new Client();
        recipientClient.setUsername("recipient");
        Wallet recipientWallet = new Wallet();
        recipientWallet.setCurrentBalance(400L);
        recipientWallet.setClient(recipientClient);

        TransferRequestDto transferRequestDto = new TransferRequestDto();
        transferRequestDto.setAmount(transferAmount);
        transferRequestDto.setRecipientUsername("recipient");

        when(walletRepository.findByClientId(senderId)).thenReturn(senderWallet);
        when(walletRepository.findByClientUsername("recipient")).thenReturn(recipientWallet);
        when(walletRepository.save(any(Wallet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Wallet updatedSenderWallet = transferBalanceUseCase.transfer(senderId, transferRequestDto);

        assertNotNull(updatedSenderWallet);
        assertEquals(400L, updatedSenderWallet.getCurrentBalance());
        assertEquals(500L, recipientWallet.getCurrentBalance());
        verify(walletRepository).save(senderWallet);
        verify(walletRepository).save(recipientWallet);
    }


    @Test
    void testTransferToSelf() {
        Long senderId = 1L;
        Long transferAmount = 100L;

        Client senderClient = new Client();
        senderClient.setId(senderId);
        senderClient.setUsername("sender");
        Wallet senderWallet = new Wallet();
        senderWallet.setCurrentBalance(500L);
        senderWallet.setClient(senderClient);

        TransferRequestDto transferRequestDto = new TransferRequestDto();
        transferRequestDto.setAmount(transferAmount);
        transferRequestDto.setRecipientUsername("sender");

        when(walletRepository.findByClientId(senderId)).thenReturn(senderWallet);

        assertThrows(Exception.class, () -> transferBalanceUseCase.transfer(senderId, transferRequestDto));
    }

    @Test
    void testTransferWithInsufficientFunds() {
        Long senderId = 1L;
        Long transferAmount = 1000L;

        Client senderClient = new Client();
        senderClient.setId(senderId);
        Wallet senderWallet = new Wallet();
        senderWallet.setCurrentBalance(100L);
        senderWallet.setClient(senderClient);

        TransferRequestDto transferRequestDto = new TransferRequestDto();
        transferRequestDto.setAmount(transferAmount);
        transferRequestDto.setRecipientUsername("recipient");

        when(walletRepository.findByClientId(senderId)).thenReturn(senderWallet);

        assertThrows(ArithmeticException.class, () -> transferBalanceUseCase.transfer(senderId, transferRequestDto));

        verify(walletRepository, never()).save(any(Wallet.class));
    }
}
