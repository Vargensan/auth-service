package org.fix.bill.auth.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationTokenEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String email;
    String activationToken;

}
