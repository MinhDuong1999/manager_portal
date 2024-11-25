package com.example.manager_users.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@SuperBuilder
@MappedSuperclass
public class CommonEntity implements Serializable {
    @CreatedBy
    @Column(name = "created_by",nullable = false, updatable = false)
    @Builder.Default
    String createUserId = "system";

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    OffsetDateTime createDate = OffsetDateTime.now();

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    @Builder.Default
    String updateUserId = "system";

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    OffsetDateTime updateDate = OffsetDateTime.now();
}
