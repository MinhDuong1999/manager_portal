package com.example.manager_users.common.filter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Slf4j
public class CustomCommonsRequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        if (!request.getRequestURI().startsWith("/actuator")) {
            log.info(message);
        }
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

    }
}
