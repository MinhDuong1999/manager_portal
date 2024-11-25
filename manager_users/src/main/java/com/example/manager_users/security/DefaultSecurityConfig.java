package com.example.manager_users.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(FwkSecurityProperties.class)
@EnableMethodSecurity
public class DefaultSecurityConfig {
    protected JWTUtil jwtUtil;
    protected FwkSecurityProperties fwkSecurityProperties;
    protected UserDetailsServiceImpl userDetailsService;
    protected CustomAccessDeniedHandler customAccessDeniedHandler;
    protected HandlerExceptionResolver handlerExceptionResolver;
    protected AuthEntryPointJwt unauthorizedHandler;
    protected SecurityContextHelper securityContextHelper;

    public DefaultSecurityConfig(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver,
                                 FwkSecurityProperties fwkSecurityProperties,
                                 JWTUtil jwtUtil,
                                 UserDetailsServiceImpl userDetailsServiceImpl,
                                 SecurityContextHelper securityContextHelper,
                                 CustomAccessDeniedHandler customAccessDeniedHandler,
                                 AuthEntryPointJwt unauthorizedHandler) {

        this.fwkSecurityProperties = fwkSecurityProperties;
        this.jwtUtil = jwtUtil;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.userDetailsService = userDetailsServiceImpl;
        this.securityContextHelper = securityContextHelper;
        this.unauthorizedHandler = unauthorizedHandler;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @ConditionalOnMissingBean
    @Bean
    public AuthJwtTokenFilter authJwtTokenFilter() {
        return new AuthJwtTokenFilter(jwtUtil, userDetailsService);
    }

    @ConditionalOnMissingBean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(unauthorizedHandler)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(getPermitAllMatchers()).permitAll()
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    protected String[] getPermitAllMatchers() {
        return fwkSecurityProperties.getExcludePattern().toArray(new String[]{});
    }

    @ConditionalOnMissingBean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @ConditionalOnMissingBean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
