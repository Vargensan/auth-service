package org.fix.bill.auth.core.domain.application.service.authentication;

import lombok.RequiredArgsConstructor;
import org.fix.bill.auth.core.domain.application.inbound.Authentication;
import org.fix.bill.auth.core.domain.application.outbound.JWTTokenProvider;
import org.fix.bill.auth.core.domain.application.outbound.PasswordManager;
import org.fix.bill.auth.core.domain.application.outbound.AuthIdentityRepository;
import org.fix.bill.auth.core.domain.exception.InvalidCredentialsException;
import org.fix.bill.auth.core.domain.model.authentication.AuthData;
import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;
import org.fix.bill.auth.core.domain.model.authentication.AuthResponse;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthenticationService implements Authentication {

    private final AuthIdentityRepository authIdentityRepository;
    private final PasswordManager passwordManager;
    private final JWTTokenProvider tokenProvider;

    @Override
    public AuthResponse authenticate(AuthData authData) {
        Optional<AuthIdentity> authIdentity = authIdentityRepository.findByEmail(authData.getEmail());
        authIdentity.ifPresent(identity -> validate(identity, authData));

        return authIdentity.map(this::buildResponse)
                .orElseThrow(InvalidCredentialsException::new);
    }

    private AuthResponse buildResponse(AuthIdentity authIdentity) {
        String token = tokenProvider.getAccessToken(authIdentity);
        String refreshToken = tokenProvider.getRefreshToken(authIdentity);

        return AuthResponse.builder()
                .withAccessToken(token)
                .withRefreshToken(refreshToken)
                .withEmail(authIdentity.getEmail())
                .withRoles(authIdentity.getRoles())
                .withTokenType("Bearer")
                .build();
    }

    public void validate(AuthIdentity authIdentity, AuthData authData) {
        String password = authData.getPassword();
        if (!isPasswordValid(authIdentity, password)) {
            throw new InvalidCredentialsException();
        }
    }

    private boolean isPasswordValid(AuthIdentity authIdentity, String password) {
        return passwordManager.verify(password, authIdentity.getHashedPassword());
    }

}
