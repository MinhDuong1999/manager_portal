package com.example.manager_users.auth.service.impl;

import com.example.manager_users.common.entity.RememberMeEntity;
import com.example.manager_users.common.repository.RefreshTokenRepository;
import com.example.manager_users.security.JWTProperties;
import com.example.manager_users.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.util.Base64;

@Service
@Slf4j
public class RefreshTokenServiceImpl {
    protected final JWTUtil jwtUtil;
    private final SecureRandom random = new SecureRandom();
    private static final int SERIES_LENGTH = 16;
    private static final int TOKEN_LENGTH = 16;
    protected final RefreshTokenRepository refreshTokenRepository;


    public RefreshTokenServiceImpl(JWTUtil jwtUtil, JWTProperties jwtProperties, RefreshTokenRepository refreshTokenRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    protected String generateTokenData() {
        byte[] newToken = new byte[TOKEN_LENGTH];
        this.random.nextBytes(newToken);
        return new String(Base64.getEncoder().encode(newToken));
    }

    protected String generateSeriesData() {
        byte[] newSeries = new byte[SERIES_LENGTH];
        this.random.nextBytes(newSeries);
        return new String(Base64.getEncoder().encode(newSeries));
    }

    public RememberMeEntity generateNewToken(String username) {
        RememberMeEntity rememberMeEntity = new RememberMeEntity(this.generateSeriesData(), username, this.generateTokenData(), OffsetDateTime.now());
        try {
            this.refreshTokenRepository.deleteAllByUsername(username);
            this.refreshTokenRepository.save(rememberMeEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return rememberMeEntity;
    }
}
