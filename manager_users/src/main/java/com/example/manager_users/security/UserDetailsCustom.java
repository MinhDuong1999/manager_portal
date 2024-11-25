package com.example.manager_users.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsCustom extends UserDetails {
    default String getTimeZoneId() {
        return "GMT+00:00";
    }

    String getRole();

    boolean isActive();

    String getUserId();
}
