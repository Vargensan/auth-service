package org.fix.bill.auth.infrastructure.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class JWTKeysConfig {

    @Bean
    public PrivateKey jwtPrivateKey() throws Exception {
        return KeyUtils.getPrivateKey("keys/jwt_private.pem");
    }

    @Bean
    public PublicKey jwtPublicKey() throws Exception {
        return KeyUtils.getPublicKey("keys/jwt_public.pem");
    }

}
