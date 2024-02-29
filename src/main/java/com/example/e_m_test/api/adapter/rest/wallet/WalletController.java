package com.example.e_m_test.api.adapter.rest.wallet;

import com.example.e_m_test.api.adapter.rest.wallet.dto.TransferRequestDto;
import com.example.e_m_test.api.adapter.rest.wallet.dto.WalletDtoMapper;
import com.example.e_m_test.api.adapter.rest.wallet.dto.WalletInfoDto;
import com.example.e_m_test.api.app.api.wallet.TransferBalanceInBound;
import com.example.e_m_test.api.app.impl.security.JwtService;
import com.example.e_m_test.api.domain.wallet.Wallet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController {

    private final JwtService jwtService;
    private final TransferBalanceInBound transferBalanceInBound;
    private final WalletDtoMapper walletDtoMapper;

    @PostMapping("/transfer")
    public ResponseEntity<WalletInfoDto> transfer(
            @Valid @RequestBody TransferRequestDto request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        Wallet result = transferBalanceInBound.transfer(
                jwtService.getJwtAuth().getClientId(), request);
        return ResponseEntity.ok(walletDtoMapper.mapToDto(result));

    }
}
