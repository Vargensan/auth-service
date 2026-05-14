package org.fix.bill.auth.core.domain.model.authentication;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;

@Value
@Builder(setterPrefix = "with", toBuilder = true)
public class AuthIdentity {

    Long id;
    String email;
    String hashedPassword;
    @Singular
    Set<String> roles;
    @Builder.Default
    boolean isActive = false;

}
