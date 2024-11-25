package com.example.manager_users.security;

import com.example.manager_users.security.constant.JWTConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTUtil {
    final JWTProperties jwtProperties;
    protected static final Integer TOKEN_AT_POSITION = 7;

    private SecretKey getKey() {
        String secretKey = jwtProperties.getSecret();
        if (StringUtils.isBlank(secretKey)) {
            throw new IllegalArgumentException("secret key is required !!!");
        }
        return Keys.hmacShaKeyFor(StringUtils.rightPad(secretKey, 64, StringUtils.SPACE).getBytes());
    }

    public String generateToken(UserDetailsCustom userPrincipal) {
        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim(JWTConstant.USER_NAME, userPrincipal.getUsername())
                .claim(JWTConstant.ACCOUNT_ID, userPrincipal.getUserId())
                .claim(JWTConstant.ROLE, userPrincipal.getRole())
                .claim(JWTConstant.CLAIMS_TIME_ZONE_ID, userPrincipal.getTimeZoneId())
                .issuedAt(new Date())
                .expiration(calculateExpireDate(jwtProperties.getExpireTimeAccessToken()))
                .signWith(getKey())
                .compact();
    }

    public Date calculateExpireDate(long expireTime) {
        return Date.from(Instant.now().plus(expireTime, ChronoUnit.MILLIS));
    }

    public String generateRefreshToken(UserDetailsCustom userPrincipal) {
        return Jwts.builder()
                .id(userPrincipal.getUserId())
                .subject(userPrincipal.getUsername())
                .claim(JWTConstant.ROLE, userPrincipal.getRole())
                .issuedAt(new Date())
                .expiration(calculateExpireDate(jwtProperties.getExpireTimeRefreshToken()))
                .signWith(getKey())
                .compact();
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(jwtProperties.getHeader());
        if (org.springframework.util.StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(TOKEN_AT_POSITION);
        }
        return null;
    }

    public Claims getClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {} {}", token, e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {} {}", token, e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {} {}", token, e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {} {}", token, e.getMessage());
        }

        return false;
    }
}