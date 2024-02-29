package com.example.e_m_test.api.adapter.rest.client.write;

import com.example.e_m_test.api.adapter.rest.client.dto.*;
import com.example.e_m_test.api.app.api.client.*;
import com.example.e_m_test.api.app.impl.security.JwtService;
import com.example.e_m_test.api.domain.client.Client;
import com.example.e_m_test.api.domain.client.Email;
import com.example.e_m_test.api.domain.client.Phone;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class V1WriteClientController {
    private final JwtService jwtService;
    private final EmailDomainMapper emailDomainMapper;
    private final AddClientEmailInBound addClientEmailInBound;
    private final EmailDtoMapper emailDtoMapper;
    private final PhoneDomainMapper phoneDomainMapper;
    private final PhoneDtoMapper phoneDtoMapper;
    private final AddClientPhoneInBound addClientPhoneInBound;
    private final UpdateClientEmailInBound updateClientEmailInBound;
    private final UpdateClientPhoneInBound updateClientPhoneInBound;
    private final DeleteClientEmailInBound deleteClientEmailInBound;
    private final DeleteClientPhoneInBound deleteClientPhoneInBound;

    @PostMapping("/client/add-email")
    public ResponseEntity<ClientInfoDto> addEmail(
            @Valid @RequestBody AddClientEmailRequestDto request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new EmailValidationException();
        }
        Email email = emailDomainMapper.mapToDomain(request);
        Client result = addClientEmailInBound.add(jwtService.getJwtAuth().getClientId(), email);
        return ResponseEntity.ok(emailDtoMapper.mapToDto(result));
    }

    @PostMapping("/client/add-phone")
    public ResponseEntity<ClientInfoDto> addPhone(
            @Valid @RequestBody AddClientPhoneRequestDto request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new PhoneValidationException();
        }
        Phone phone = phoneDomainMapper.mapToDomain(request);
        Client result = addClientPhoneInBound.add(jwtService.getJwtAuth().getClientId(), phone);
        return ResponseEntity.ok(phoneDtoMapper.mapToDto(result));
    }

    @PutMapping("/client/change-email")
    ResponseEntity<ClientInfoDto> updateEmail(
            @Valid @RequestBody UpdateClientEmailRequestDto request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new EmailValidationException();
        }
        Client result = updateClientEmailInBound.update(jwtService.getJwtAuth().getClientId(), request);
        return ResponseEntity.ok(emailDtoMapper.mapToDto(result));
    }

    @PutMapping("/client/change-phone")
    ResponseEntity<ClientInfoDto> updatePhone(
            @Valid @RequestBody UpdateClientPhoneRequestDto request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new PhoneValidationException();
        }
        Client result = updateClientPhoneInBound.update(jwtService.getJwtAuth().getClientId(), request);
        return ResponseEntity.ok(phoneDtoMapper.mapToDto(result));
    }

    @DeleteMapping("/client/delete-email")
    ResponseEntity<ClientInfoDto> deleteEmail(
            @Valid @RequestBody DeleteClientEmailRequestDto request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new EmailValidationException();
        }
        Email email = emailDomainMapper.mapToDomain(request);
        Client result = deleteClientEmailInBound.delete(jwtService.getJwtAuth().getClientId(), email);
        return ResponseEntity.ok(emailDtoMapper.mapToDto(result));
    }

    @DeleteMapping("/client/delete-phone")
    ResponseEntity<ClientInfoDto> deletePhone(
            @Valid @RequestBody DeleteClientPhoneRequestDto request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new PhoneValidationException();
        }
        Phone phone = phoneDomainMapper.mapToDomain(request);
        Client result = deleteClientPhoneInBound.delete(jwtService.getJwtAuth().getClientId(), phone);
        return ResponseEntity.ok(phoneDtoMapper.mapToDto(result));
    }
}