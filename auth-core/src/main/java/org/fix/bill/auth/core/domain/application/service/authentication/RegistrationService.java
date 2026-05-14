package org.fix.bill.auth.core.domain.application.service.authentication;

import lombok.RequiredArgsConstructor;
import org.fix.bill.auth.core.domain.application.inbound.Registration;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataPublisher;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataRepository;
import org.fix.bill.auth.core.domain.application.outbound.AuthIdentityRepository;
import org.fix.bill.auth.core.domain.application.outbound.PasswordManager;
import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;
import org.fix.bill.auth.core.domain.model.authentication.AuthData;
import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class RegistrationService implements Registration {

    private final PasswordManager passwordManager;
    private final VerificationTokenDataPublisher verificationTokenDataPublisher;
    private final VerificationTokenDataRepository verificationTokenDataRepository;
    private final AuthIdentityRepository authIdentityRepository;

    @Override
    public void register(AuthData authData) {
        validateNonExistence(authData);
        AuthIdentity authIdentity = store(authData);
        requestActivation(authIdentity);
    }

    private void validateNonExistence(AuthData authData) {
        Optional<AuthIdentity> authIdentity = authIdentityRepository.findByEmail(authData.getEmail());
        if (authIdentity.isPresent()) {
           throw new RuntimeException("Email already in use.");
        }
    }

    private AuthIdentity store(AuthData authData) {
        AuthIdentity authIdentity = getAuthIdentity(authData);
        return authIdentityRepository.save(authIdentity);
    }

    private AuthIdentity getAuthIdentity(AuthData authData) {
        String hashedPassword = passwordManager.hash(authData.getPassword());
        return AuthIdentity.builder()
                .withRole("user")
                .withHashedPassword(hashedPassword)
                .withEmail(authData.getEmail())
                .withIsActive(false)
                .build();
    }

    private VerificationTokenData getActivationRequiredEvent(AuthIdentity authIdentity) {
        return VerificationTokenData.builder()
                .withEmail(authIdentity.getEmail())
                .withActivationToken(UUID.randomUUID().toString())
                .build();
    }

    private void requestActivation(AuthIdentity authIdentity) {
        VerificationTokenData verificationTokenData = getActivationRequiredEvent(authIdentity);

        verificationTokenDataRepository.save(verificationTokenData);
        verificationTokenDataPublisher.publish(verificationTokenData);
    }

}
