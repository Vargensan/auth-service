package org.fix.bill.auth.core.domain.application.outbound;

import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;

import java.util.Optional;

public interface VerificationTokenDataRepository {

    void save(VerificationTokenData event);

    Optional<VerificationTokenData> findByActivationToken(String activationToken);

    void removeByActivationToken(String activationToken);

}
