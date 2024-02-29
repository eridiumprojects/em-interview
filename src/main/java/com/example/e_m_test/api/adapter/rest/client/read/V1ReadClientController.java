package com.example.e_m_test.api.adapter.rest.client.read;

import com.example.e_m_test.api.adapter.rest.client.dto.ClientDtoMapper;
import com.example.e_m_test.api.adapter.rest.client.dto.ClientInfoDto;
import com.example.e_m_test.api.app.api.client.GetClientInBound;
import com.example.e_m_test.api.app.api.client.SearchClientsInBound;
import com.example.e_m_test.api.app.impl.security.JwtService;
import com.example.e_m_test.api.domain.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class V1ReadClientController {
    private final GetClientInBound getClientInBound;
    private final JwtService jwtService;
    private final ClientDtoMapper clientDtoMapper;
    private final SearchClientsInBound searchClientsInBound;

    @GetMapping("/client")
    public ResponseEntity<ClientInfoDto> getClient() {
        Client result = getClientInBound.get(jwtService.getJwtAuth().getClientId());
        return ResponseEntity.ok(clientDtoMapper.mapToDto(result));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientInfoDto>> searchClients(
            @RequestParam(value = "birth", required = false) String birth,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Client> clients = searchClientsInBound.searchByFilters(
                birth, phone, name, email, page, size
        );
        return ResponseEntity.ok(clientDtoMapper.mapToDto(clients));
    }
}