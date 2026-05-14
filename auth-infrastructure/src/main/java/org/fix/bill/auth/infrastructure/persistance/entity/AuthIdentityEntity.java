package org.fix.bill.auth.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthIdentityEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String email;
    @Column(length = 512)
    private String passwordHash;
    private boolean isActivated;

}
