package org.fix.bill.auth.app.config;

import org.fix.bill.auth.core.domain.application.inbound.Activation;
import org.fix.bill.auth.core.domain.application.outbound.AuthIdentityRepository;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataRepository;
import org.fix.bill.auth.core.domain.application.service.authentication.ActivationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivationServiceConfig {

    @Bean
    public Activation getActivationService(VerificationTokenDataRepository verificationTokenDataRepository,
                                           AuthIdentityRepository authIdentityRepository) {
        return new ActivationService(verificationTokenDataRepository, authIdentityRepository);
    }

}
