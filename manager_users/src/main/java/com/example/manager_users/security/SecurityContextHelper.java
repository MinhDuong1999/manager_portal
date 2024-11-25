package com.example.manager_users.security;

import com.example.manager_users.common.entity.UserEntity;
import com.example.manager_users.common.exception.AuthenticationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component(value = "securityContextHelper")
public class SecurityContextHelper {
    public UserDetailsImpl getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserDetailsImpl) authentication.getPrincipal();
        }
        throw new AuthenticationException();
    }

    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || (authentication instanceof AnonymousAuthenticationToken)) {
            return null;
        }
        return getUserDetails().user();
    }
}

