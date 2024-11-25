package com.example.manager_users.common.repository;

import com.example.manager_users.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserName(String username);

    boolean existsByUserNameAndProvider(String username, String provider);

    List<UserEntity> findAllByIsActiveIsTrue();
}
