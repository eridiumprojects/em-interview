package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.LoginClientInBound;
import com.example.e_m_test.api.app.api.security.DeviceRepository;
import com.example.e_m_test.api.app.api.security.JwtResponse;
import com.example.e_m_test.api.app.api.security.RefreshTokenRepository;
import com.example.e_m_test.api.app.api.wallet.WalletRepository;
import com.example.e_m_test.api.app.impl.security.AuthException;
import com.example.e_m_test.api.app.impl.security.JwtService;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.security.Device;
import com.example.e_m_test.api.domain.security.ERole;
import com.example.e_m_test.api.domain.security.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.e_m_test.api.app.api.security.AuthErrorMessages.INVALID_PASSWORD;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginClientUseCase implements LoginClientInBound {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final DeviceRepository deviceRepository;
    private final WalletRepository walletRepository;

    @Override
    @Transactional
    public JwtResponse login(String username, String password, UUID deviceToken) {
        log.info("Logging user with username {}", username);
        Client client = clientRepository.getByUsername(username);

        if (!passwordEncoder.matches(password, client.getPassword())) {
            throw new AuthException(INVALID_PASSWORD);
        }

        var curDevice = deviceRepository.findByDeviceToken(deviceToken)
                .orElse(Device.createDevice(deviceToken, client));
        curDevice = deviceRepository.save(curDevice);

        var wallet = walletRepository.findByClient(client);
        wallet.setInitialBalance(client.getWallet().getInitialBalance());
        walletRepository.save(wallet);

        var tokens = jwtService.generateAccessRefreshTokens(
                client.getUsername(), client.getId(), curDevice.getId(), ERole.USER);

        var refreshToken = new RefreshToken();
        refreshToken.setToken(tokens.getRefreshToken());
        refreshToken.setClient(client);
        refreshTokenRepository.save(refreshToken);

        return tokens;
    }
}
