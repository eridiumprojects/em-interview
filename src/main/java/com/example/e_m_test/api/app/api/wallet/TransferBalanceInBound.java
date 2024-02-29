package com.example.e_m_test.api.app.api.wallet;

import com.example.e_m_test.api.adapter.rest.wallet.dto.TransferRequestDto;
import com.example.e_m_test.api.domain.wallet.Wallet;

public interface TransferBalanceInBound {
    Wallet transfer(Long clientId,TransferRequestDto wallet);
}
