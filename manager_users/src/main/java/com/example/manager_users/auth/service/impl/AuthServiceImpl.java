package com.example.manager_users.auth.service.impl;

import com.example.manager_users.auth.dto.request.LoginRequest;
import com.example.manager_users.auth.dto.response.LoginResponse;
import com.example.manager_users.auth.service.AuthService;
import com.example.manager_users.common.exception.AuthenticationException;
import com.example.manager_users.common.repository.UserRepository;
import com.example.manager_users.security.JWTUtil;
import com.example.manager_users.security.UserDetailsCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.manager_users.auth.constant.DataConstant.LOCAL;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest authLogin) {
        return doLogin(authLogin.getUsername(), authLogin.getPassword());
    }

    public LoginResponse doLogin(String username, String password) {
        Authentication authentication;
        boolean isExisted = userRepository.existsByUserNameAndProvider(username, LOCAL);
        if (!isExisted) {
            throw new UsernameNotFoundException("User not found with id: " + username);
        }

        authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetailsCustom userDetailsCustom = (UserDetailsCustom) authentication.getPrincipal();
        if (Boolean.FALSE.equals(userDetailsCustom.isActive())) {
            throw new AuthenticationException("User is not active");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsCustom userPrincipal = (UserDetailsCustom) authentication.getPrincipal();

        var token = jwtUtil.generateToken(userPrincipal);
        String refreshToken = createRefreshToken(userPrincipal);

        return LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    private String createRefreshToken(UserDetailsCustom userPrincipal) {
        return jwtUtil.generateRefreshToken(userPrincipal);
    }
}
