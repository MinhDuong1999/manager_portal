package com.example.manager_users.security;

import com.example.manager_users.common.entity.UserEntity;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Builder
public record UserDetailsImpl(UserEntity user) implements UserDetailsCustom {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public String getRole() {
        return this.user.getRole();
    }

    @Override
    public boolean isActive() {
        return this.user.getIsActive();
    }

    @Override
    public String getUserId() {
        return this.user.getId();
    }
}
