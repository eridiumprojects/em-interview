package com.example.e_m_test.api.app.api.client;

import com.example.e_m_test.api.domain.client.Client;

import java.util.List;

public interface SearchClientsInBound {
    List<Client> searchByFilters(String birth, String phone, String name, String email, int page, int size);
}
