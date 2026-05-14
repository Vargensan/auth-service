package org.fix.bill.auth.core.domain.application.outbound;

import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;

public interface VerificationTokenDataPublisher {

    void publish(VerificationTokenData verificationTokenData);

}
