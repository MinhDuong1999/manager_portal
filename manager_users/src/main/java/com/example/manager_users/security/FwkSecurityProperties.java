package com.example.manager_users.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "security")
public class FwkSecurityProperties {
    List<String> excludePattern = new ArrayList<>();
}
