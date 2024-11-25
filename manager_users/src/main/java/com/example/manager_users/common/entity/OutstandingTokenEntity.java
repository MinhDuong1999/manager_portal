package com.example.manager_users.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Table(name = "outstanding_token")
@Entity
@NoArgsConstructor
public class OutstandingTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "expires_at")
    private OffsetDateTime expiresAt;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "jti")
    private String jwtIdentifier;

    public OutstandingTokenEntity(String token, OffsetDateTime expiration, String userId, String jti) {
        this.token = token;
        this.expiresAt = expiration;
        this.createdAt = OffsetDateTime.now();
        this.userId = userId;
        this.jwtIdentifier = jti;
    }
}
