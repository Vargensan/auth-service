package org.fix.bill.auth.infrastructure.persistance.repository;

import lombok.RequiredArgsConstructor;
import org.fix.bill.auth.core.domain.application.outbound.VerificationTokenDataRepository;
import org.fix.bill.auth.core.domain.model.authentication.VerificationTokenData;
import org.fix.bill.auth.infrastructure.persistance.entity.VerificationTokenEntity;
import org.fix.bill.auth.infrastructure.persistance.mapper.VerificationTokenEntityMapper;
import org.fix.bill.auth.infrastructure.persistance.mapper.ActivationRequiredEventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ActivationRepository implements VerificationTokenDataRepository {

    private final ActivationJPARepository activationJPARepository;

    @Override
    @Transactional
    public void save(VerificationTokenData event) {
        VerificationTokenEntity verificationTokenEntity = VerificationTokenEntityMapper.from(event);
        activationJPARepository.save(verificationTokenEntity);
    }

    @Override
    public Optional<VerificationTokenData> findByActivationToken(String activationToken) {
        return activationJPARepository.findByActivationToken(activationToken)
                .map(ActivationRequiredEventMapper::from);
    }

    @Override
    @Transactional
    public void removeByActivationToken(String activationToken) {
        activationJPARepository.removeByActivationToken(activationToken);
    }
}
