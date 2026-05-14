package org.fix.bill.auth.infrastructure.persistance.mapper;

import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;
import org.fix.bill.auth.infrastructure.persistance.entity.AuthIdentityEntity;

public class AuthIdentityEntityMapper {

    private AuthIdentityEntityMapper() {}

    public static AuthIdentityEntity from(AuthIdentity identity) {
        AuthIdentityEntity authIdentityEntity = new AuthIdentityEntity();
        authIdentityEntity.setEmail(identity.getEmail());
        authIdentityEntity.setId(identity.getId());
        authIdentityEntity.setPasswordHash(identity.getHashedPassword());
        authIdentityEntity.setActivated(identity.isActive());
        return authIdentityEntity;
    }

}
