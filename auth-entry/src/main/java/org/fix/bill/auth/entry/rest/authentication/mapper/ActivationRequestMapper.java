package org.fix.bill.auth.entry.rest.authentication.mapper;

import org.fix.bill.auth.api.dto.ActivationRequestDTO;
import org.fix.bill.auth.core.domain.model.authentication.ActivationToken;

public class ActivationRequestMapper {

    private ActivationRequestMapper() {}

    public static ActivationToken from(ActivationRequestDTO activationRequestDTO) {
        return ActivationToken.builder()
                .token(activationRequestDTO.getActivationToken())
                .build();
    }

}
