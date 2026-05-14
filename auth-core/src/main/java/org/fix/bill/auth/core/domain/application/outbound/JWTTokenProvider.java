package org.fix.bill.auth.core.domain.application.outbound;

import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;

public interface JWTTokenProvider {

    String getAccessToken(AuthIdentity authIdentity);
    String getRefreshToken(AuthIdentity authIdentity);
}
