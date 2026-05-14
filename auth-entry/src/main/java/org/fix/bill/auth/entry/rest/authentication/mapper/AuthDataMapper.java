package org.fix.bill.auth.entry.rest.authentication.mapper;

import org.fix.bill.auth.api.dto.PasswordAuthenticationRequestDTO;
import org.fix.bill.auth.api.dto.RegisterRequestDTO;
import org.fix.bill.auth.core.domain.model.authentication.AuthData;

public class AuthDataMapper {

    private AuthDataMapper() {}

    public static AuthData from(PasswordAuthenticationRequestDTO passwordAuthenticationRequestDTO) {
        return AuthData.builder()
                .withEmail(passwordAuthenticationRequestDTO.getEmail())
                .withPassword(passwordAuthenticationRequestDTO.getPassword())
                .build();
    }

    public static AuthData from(RegisterRequestDTO registerRequestDTO) {
        return AuthData.builder()
                .withEmail(registerRequestDTO.getEmail())
                .withPassword(registerRequestDTO.getPassword())
                .build();
    }
}
