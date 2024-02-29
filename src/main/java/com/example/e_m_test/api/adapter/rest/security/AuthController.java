package com.example.e_m_test.api.adapter.rest.security;

import com.example.e_m_test.api.adapter.rest.client.dto.ClientDtoMapper;
import com.example.e_m_test.api.adapter.rest.client.dto.ClientInfoDto;
import com.example.e_m_test.api.adapter.rest.security.dto.*;
import com.example.e_m_test.api.app.api.client.RegisterClientException;
import com.example.e_m_test.api.app.api.client.RegisterClientInBound;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.app.api.client.LoginClientInBound;
import com.example.e_m_test.api.app.api.security.JwtResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ClientDtoMapper clientDtoMapper;
    private final SignupRequestDomainMapper signupRequestDomainMapper;
    private final JwtResponseDtoMapper jwtResponseDtoMapper;
    private final LoginClientInBound loginClientInBound;
    private final RegisterClientInBound registerClientInBound;


    @PostMapping("/signin")
    public ResponseEntity<JwtResponseDto> loginClient(@Valid @RequestBody LoginRequestDto request) {
        JwtResponse response = loginClientInBound.login(
                request.getUsername(), request.getPassword(), request.getDeviceToken());
        return ResponseEntity.ok(jwtResponseDtoMapper.mapToDto(response));
    }

    @PostMapping("/signup")
    public ResponseEntity<ClientInfoDto> registerUser(
            @Valid @RequestBody SignupRequestDto signUpRequestDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            throw new RegisterClientException(bindingResult.getAllErrors().toString());
        }
        Client registeredClient = registerClientInBound.register(signupRequestDomainMapper.mapToDomain(signUpRequestDto));
        return ResponseEntity.ok(clientDtoMapper.mapToDto(registeredClient));
    }
}
