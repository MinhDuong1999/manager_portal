package com.example.manager_users.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blacklisted_token")
@Entity
public class BlacklistedTokenEntity {

    @Id
    private Long id;

    @Column(name = "blacklisted_at")
    private OffsetDateTime blacklistedAt;


    @JoinColumn(name ="token_id", insertable = false, updatable = false)
    @ManyToOne
    private OutstandingTokenEntity outstandingToken;
}
