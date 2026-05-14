package org.fix.bill.auth.api.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;
import java.util.UUID;

@Value
@Builder(setterPrefix = "with")
@Jacksonized
public class AuthenticationResponseDTO {

    String accessToken;
    String refreshToken;
    long expiresIn;
    String tokenType;
    UUID userId;
    String email;
    @Singular
    Set<String> roles;


}