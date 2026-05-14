package org.fix.bill.auth.entry.rest.authentication.mapper;

import org.fix.bill.auth.api.dto.AuthenticationResponseDTO;
import org.fix.bill.auth.core.domain.model.authentication.AuthResponse;

public class AuthenticationResponseDTOMapper {

    private AuthenticationResponseDTOMapper() {}

    public static AuthenticationResponseDTO from(AuthResponse authResponse) {
        return AuthenticationResponseDTO.builder()
                .withRefreshToken(authResponse.getRefreshToken())
                .withAccessToken(authResponse.getAccessToken())
                .withEmail(authResponse.getEmail())
                .withRoles(authResponse.getRoles())
                .withUserId(authResponse.getUserId())
                .withExpiresIn(authResponse.getExpiresIn())
                .build();
    }

}
