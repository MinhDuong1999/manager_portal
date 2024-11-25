package com.example.manager_users.auth.controller;

import com.example.manager_users.auth.dto.request.LoginRequest;
import com.example.manager_users.auth.dto.response.LoginResponse;
import com.example.manager_users.auth.service.AuthService;
import com.example.manager_users.common.dto.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.manager_users.auth.constant.AuthApiEndpoint.AUTH_ENDPOINT;
import static org.springframework.messaging.simp.stomp.StompHeaders.LOGIN;

@RestController
@RequestMapping(AUTH_ENDPOINT)
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(LOGIN)
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return CommonResponse.createSuccessData(authService.login(request));
    }
}
