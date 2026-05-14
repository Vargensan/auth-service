package org.fix.bill.auth.infrastructure.persistance.repository;

import org.fix.bill.auth.infrastructure.persistance.entity.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationJPARepository extends JpaRepository<VerificationTokenEntity,Long> {

    Optional<VerificationTokenEntity> findByActivationToken(String activationToken);

    void removeByActivationToken(String activationToken);

}
