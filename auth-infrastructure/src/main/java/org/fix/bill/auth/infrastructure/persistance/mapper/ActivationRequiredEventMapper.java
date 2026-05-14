package org.fix.bill.auth.infrastructure.persistance.mapper;

import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;
import org.fix.bill.auth.infrastructure.persistance.entity.VerificationTokenEntity;

public class ActivationRequiredEventMapper {

    private ActivationRequiredEventMapper() {
    }

    public static VerificationTokenData from(VerificationTokenEntity verificationTokenEntity) {
        return VerificationTokenData.builder()
                .withEmail(verificationTokenEntity.getEmail())
                .withActivationToken(verificationTokenEntity.getActivationToken())
                .build();
    }

}
