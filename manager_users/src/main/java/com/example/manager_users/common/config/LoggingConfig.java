package com.example.manager_users.common.config;

import com.example.manager_users.common.filter.CustomCommonsRequestLoggingFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    @Bean
    public CustomCommonsRequestLoggingFilter loggingFilter() {
        CustomCommonsRequestLoggingFilter filter = new CustomCommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(false);
        filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);
        filter.setBeforeMessagePrefix(StringUtils.SPACE);
        filter.setBeforeMessageSuffix(StringUtils.SPACE);
        return filter;
    }
}
