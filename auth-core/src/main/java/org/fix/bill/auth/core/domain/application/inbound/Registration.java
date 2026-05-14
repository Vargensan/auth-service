package org.fix.bill.auth.core.domain.application.inbound;

import org.fix.bill.auth.core.domain.model.authentication.AuthData;

public interface Registration {

    void register(AuthData authData);
}
