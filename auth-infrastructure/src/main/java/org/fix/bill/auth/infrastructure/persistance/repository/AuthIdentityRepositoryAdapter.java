package org.fix.bill.auth.infrastructure.persistance.repository;

import lombok.RequiredArgsConstructor;
import org.fix.bill.auth.core.domain.application.outbound.AuthIdentityRepository;
import org.fix.bill.auth.core.domain.model.authentication.AuthIdentity;
import org.fix.bill.auth.infrastructure.persistance.entity.AuthIdentityEntity;
import org.fix.bill.auth.infrastructure.persistance.mapper.AuthIdentityEntityMapper;
import org.fix.bill.auth.infrastructure.persistance.mapper.AuthIdentityMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthIdentityRepositoryAdapter implements AuthIdentityRepository {

    private final AuthIdentityJPARepository authIdentityJPARepository;

    @Override
    public Optional<AuthIdentity> findByEmail(String email) {
        return authIdentityJPARepository.findByEmail(email)
                .map(AuthIdentityMapper::from);
    }

    @Override
    @Transactional
    public AuthIdentity save(AuthIdentity user) {
        AuthIdentityEntity from = AuthIdentityEntityMapper.from(user);
        AuthIdentityEntity savedUser = authIdentityJPARepository.save(from);
        return AuthIdentityMapper.from(savedUser);
    }

}
