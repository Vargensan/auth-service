package org.fix.bill.auth.infrastructure.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.fix.bill.auth.core.domain.application.outbound.JWTTokenProvider;
import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JJWTProvider implements JWTTokenProvider {

    private final JWTProperties jwtProperties;
    private final PrivateKey privateKey;

    @Override
    public String getAccessToken(AuthIdentity authIdentity) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(jwtProperties.getAccessTokenExpirationSeconds());

        return Jwts.builder()
                .setSubject(authIdentity.getId().toString())
                .claim("email", authIdentity.getEmail())
                .claim("roles", authIdentity.getRoles().stream().toList())
                .claim("isActive", authIdentity.isActive())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public String getRefreshToken(AuthIdentity authIdentity) {
        Instant now = Instant.now();
        Instant expiry = now.plusSeconds(jwtProperties.getRefreshTokenExpirationSeconds());

        return Jwts.builder()
                .setSubject(authIdentity.getId().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

}
