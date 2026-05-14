package org.fix.bill.auth.app.config;

import org.fix.bill.auth.core.domain.application.inbound.Authentication;
import org.fix.bill.auth.core.domain.application.outbound.AuthIdentityRepository;
import org.fix.bill.auth.core.domain.application.outbound.JWTTokenProvider;
import org.fix.bill.auth.core.domain.application.outbound.PasswordManager;
import org.fix.bill.auth.core.domain.application.service.authentication.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationServiceConfig {

    @Bean
    public Authentication getAuthenticationService(AuthIdentityRepository authIdentityRepository,
                                                   PasswordManager passwordManager,
                                                   JWTTokenProvider jwtTokenProvider) {
        return new AuthenticationService(authIdentityRepository,
                passwordManager,
                jwtTokenProvider);
    }

}
