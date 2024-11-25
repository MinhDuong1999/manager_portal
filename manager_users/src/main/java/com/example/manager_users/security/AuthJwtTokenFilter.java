package com.example.manager_users.security;

import com.example.manager_users.security.constant.JWTConstant;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AuthJwtTokenFilter extends OncePerRequestFilter {

    protected final JWTUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtUtil.parseJwt(request);
            if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
                log.info("JWT token validated : {}", jwt);
                Claims claims = jwtUtil.getClaims(jwt);
                String userName = claims.getSubject();
                String role = claims.get(JWTConstant.ROLE, String.class);
                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
                UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(userName);
                if (role.equals(userDetails.getRole())) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    setLocaleAndTimeZone(request, claims);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication:{}", e);
        }
        filterChain.doFilter(request, response);
    }

    protected void setAuthUserInfo(HttpServletRequest request, Claims claims) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put(JWTConstant.ACCOUNT_ID, claims.getId());
        request.setAttribute(JWTConstant.AUTH_USER_INFO_ATTRIBUTE_NAME, userInfo);
    }

    protected void setLocaleAndTimeZone(HttpServletRequest request, Claims claims) {
        setAuthUserInfo(request, claims);

        String lang = claims.get("language", String.class);
        String timeZone = claims.get("timeZone", String.class);

        if (lang != null) {
            request.setAttribute(JWTConstant.LOCALE_JWT_ATTRIBUTE_NAME, new Locale(lang));
        }

        if (timeZone != null) {
            log.info("with timeZone = {}", timeZone);
            request.setAttribute(JWTConstant.TIMEZONE_JWT_ATTRIBUTE_NAME, timeZone);
        }
    }
}
