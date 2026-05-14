package org.fix.bill.auth.app.config;

import org.fix.bill.auth.core.domain.application.inbound.Registration;
import org.fix.bill.auth.core.domain.application.outbound.AuthIdentityRepository;
import org.fix.bill.auth.core.domain.application.outbound.PasswordManager;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataPublisher;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataRepository;
import org.fix.bill.auth.core.domain.application.service.authentication.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RegistrationServiceConfig {

    @Bean
    public Registration getRegistrationService(PasswordManager passwordManager,
                                               VerificationTokenDataPublisher verificationTokenDataPublisher,
                                               VerificationTokenDataRepository verificationTokenDataRepository,
                                               AuthIdentityRepository authIdentityRepository) {
    return new RegistrationService(passwordManager,
                verificationTokenDataPublisher,
                verificationTokenDataRepository,
                authIdentityRepository);
    }

}
