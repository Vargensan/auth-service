package org.fix.bill.auth.core.domain.model.authentication;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class VerificationTokenData {

    String email;
    String activationToken;

}
