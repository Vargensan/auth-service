package org.fix.bill.auth.infrastructure.persistance.mapper;

import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;
import org.fix.bill.auth.infrastructure.persistance.entity.AuthIdentityEntity;

public class AuthIdentityMapper {

    private AuthIdentityMapper(){}

    public static AuthIdentity from(AuthIdentityEntity authIdentityEntity) {
        return AuthIdentity.builder()
                .withHashedPassword(authIdentityEntity.getPasswordHash())
                .withEmail(authIdentityEntity.getEmail())
                .withId(authIdentityEntity.getId())
                .withIsActive(authIdentityEntity.isActivated())
                .build();
    }

}
