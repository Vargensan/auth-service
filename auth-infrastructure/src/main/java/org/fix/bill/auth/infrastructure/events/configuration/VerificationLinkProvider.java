package org.fix.bill.auth.infrastructure.events.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "domain.verification")
@Data
public class VerificationLinkProvider {

    private String address;

}
