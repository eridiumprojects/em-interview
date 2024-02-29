package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.app.api.client.ClientRepository;
import com.example.e_m_test.api.app.api.client.GetClientInBound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetClientUseCase implements GetClientInBound {
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = true)
    public Client get(Long clientId) {
        log.info("Getting client info for id {}", clientId);
        return clientRepository.getById(clientId);
    }
}
