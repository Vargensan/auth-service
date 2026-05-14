package org.fix.bill.auth.infrastructure.persistance.mapper;

import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;
import org.fix.bill.auth.infrastructure.persistance.entity.VerificationTokenEntity;

public class VerificationTokenEntityMapper {

    private VerificationTokenEntityMapper(){}

    public static VerificationTokenEntity from(VerificationTokenData event) {
        String activationToken = event.getActivationToken();
        String email = event.getEmail();
        VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity();
        verificationTokenEntity.setActivationToken(activationToken);
        verificationTokenEntity.setEmail(email);
        return verificationTokenEntity;
    }

}
