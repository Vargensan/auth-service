package org.fix.bill.auth.core.domain.model.authentication;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder(setterPrefix = "with")
public class AuthResponse {

    String accessToken;
    String refreshToken;
    long expiresIn;
    String tokenType;
    UUID userId;
    String email;
    @Singular
    Set<String> roles;

}
