package org.fix.bill.auth.core.domain.application.outbound;

import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;

import java.util.Optional;

public interface AuthIdentityRepository {

    Optional<AuthIdentity> findByEmail(String email);
    AuthIdentity save(AuthIdentity user);

}
