package org.fix.bill.auth.entry.rest.authentication.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fix.bill.auth.api.dto.ActivationRequestDTO;
import org.fix.bill.auth.api.dto.AuthenticationResponseDTO;
import org.fix.bill.auth.api.dto.PasswordAuthenticationRequestDTO;
import org.fix.bill.auth.api.dto.RegisterRequestDTO;
import org.fix.bill.auth.core.domain.application.inbound.Activation;
import org.fix.bill.auth.core.domain.application.inbound.Authentication;
import org.fix.bill.auth.core.domain.application.inbound.Registration;
import org.fix.bill.auth.core.domain.model.authentication.ActivationToken;
import org.fix.bill.auth.core.domain.model.authentication.AuthData;
import org.fix.bill.auth.core.domain.model.authentication.AuthResponse;
import org.fix.bill.auth.entry.rest.authentication.mapper.ActivationRequestMapper;
import org.fix.bill.auth.entry.rest.authentication.mapper.AuthDataMapper;
import org.fix.bill.auth.entry.rest.authentication.mapper.AuthenticationResponseDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final Authentication authentication;
    private final Registration registration;
    private final Activation activation;

    @PostMapping("/login")
    public AuthenticationResponseDTO login(@Valid @RequestBody PasswordAuthenticationRequestDTO requestDTO) {
        AuthData credentials = AuthDataMapper.from(requestDTO);
        AuthResponse authenticationResult = authentication.authenticate(credentials);
        return AuthenticationResponseDTOMapper.from(authenticationResult);
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        AuthData credentials = AuthDataMapper.from(registerRequestDTO);
        registration.register(credentials);
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/activate")
    @Transactional
    public ResponseEntity<?> activate(@Valid @RequestBody ActivationRequestDTO activationRequestDTO) {
        ActivationToken activationToken = ActivationRequestMapper.from(activationRequestDTO);
        activation.activate(activationToken);
        return ResponseEntity.ok()
                .build();
    }

}
