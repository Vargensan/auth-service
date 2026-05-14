package org.fix.bill.auth.core.domain.application.inbound;

import org.fix.bill.auth.core.domain.model.authentication.AuthData;
import org.fix.bill.auth.core.domain.model.authentication.AuthResponse;

public interface Authentication {

    AuthResponse authenticate(AuthData authData);

}
