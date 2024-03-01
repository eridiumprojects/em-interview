package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.app.api.client.PhoneNotFoundException;
import com.example.e_m_test.api.app.api.client.PhoneRepository;
import com.example.e_m_test.api.domain.client.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhoneRepositoryImpl implements PhoneRepository {
    private final PhoneJpaRepository phoneJpaRepository;

    @Override
    public Phone findByNumber(String number) {
        return phoneJpaRepository.findByNumber(number)
                .orElseThrow(() -> new PhoneNotFoundException(number));
    }

    @Override
    public boolean existsByNumber(String number) {
        return phoneJpaRepository.existsByNumber(number);
    }

    @Override
    public Phone save(Phone phone) {
        return phoneJpaRepository.save(phone);
    }

    @Override
    public void deleteByNumber(String number) {
        phoneJpaRepository.deleteByNumber(number);
    }

    @Override
    public boolean existsByNumberAndClientId(String number, Long clientId) {
        return phoneJpaRepository.existsByNumberAndClientId(number, clientId);
    }
}