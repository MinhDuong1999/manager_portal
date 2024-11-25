package com.example.manager_users.common.repository;

import com.example.manager_users.common.entity.RememberMeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RememberMeEntity, String> {
    void deleteAllByUsername(String username);
}
