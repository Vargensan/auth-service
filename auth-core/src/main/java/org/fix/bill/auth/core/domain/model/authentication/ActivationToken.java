package org.fix.bill.auth.core.domain.model.authentication;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ActivationToken {

    String token;

}
