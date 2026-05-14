package org.fix.bill.auth.infrastructure.persistance.repository;

import org.fix.bill.auth.infrastructure.persistance.entity.AuthIdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthIdentityJPARepository extends JpaRepository<AuthIdentityEntity, UUID> {

    Optional<AuthIdentityEntity> findByEmail(String email);

}
