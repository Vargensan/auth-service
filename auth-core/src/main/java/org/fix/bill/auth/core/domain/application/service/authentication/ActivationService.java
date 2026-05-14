package org.fix.bill.auth.core.domain.application.service.authentication;

import lombok.RequiredArgsConstructor;

import org.fix.bill.auth.core.domain.application.outbound.AuthIdentityRepository;
import org.fix.bill.auth.core.domain.application.inbound.Activation;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataRepository;
import org.fix.bill.auth.core.domain.model.authentication.ActivationToken;
import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;
import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;

import java.util.Optional;

@RequiredArgsConstructor
public class ActivationService implements Activation {

    private final VerificationTokenDataRepository verificationTokenDataRepository;
    private final AuthIdentityRepository authIdentityRepository;

    @Override
    public void activate(ActivationToken activationToken) {
        Optional<VerificationTokenData> verificationToken = verificationTokenDataRepository.findByActivationToken(activationToken.getToken());
        boolean isActivated = verificationToken.map(VerificationTokenData::getEmail)
                .flatMap(authIdentityRepository::findByEmail)
                .map(this::activate)
                .orElse(Boolean.FALSE);

        if (isActivated) {
            verificationTokenDataRepository.removeByActivationToken(activationToken.getToken());
        }
    }

    public boolean activate(AuthIdentity authIdentity) {
        AuthIdentity activatedIdentity = getAuthIdentityMarkedAsActive(authIdentity);
        authIdentityRepository.save(activatedIdentity);
        return true;
    }

    public AuthIdentity getAuthIdentityMarkedAsActive(AuthIdentity authIdentity) {
        return authIdentity.toBuilder()
                .withIsActive(true)
                .build();
    }

}
