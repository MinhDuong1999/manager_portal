package com.example.manager_users.auth.service;

import com.example.manager_users.auth.dto.request.LoginRequest;
import com.example.manager_users.auth.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest authLogin);
}
