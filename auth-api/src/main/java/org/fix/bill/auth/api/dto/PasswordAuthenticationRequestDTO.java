package org.fix.bill.auth.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(setterPrefix = "with")
@Jacksonized
public class PasswordAuthenticationRequestDTO {

    String email;
    String password;

}
