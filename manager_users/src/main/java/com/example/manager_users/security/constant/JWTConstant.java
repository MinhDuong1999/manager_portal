package com.example.manager_users.security.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTConstant {
    public static final String ROLE = "role";
    public static final String USER_NAME = "userName";
    public static final String CLAIMS_REMEMBER_ME = "rememberMe";
    public static final String CLAIMS_REMEMBER_ME_SERIES = "series";
    public static final String CLAIMS_REMEMBER_ME_TOKEN = "tokenValue";
    public static final String CLAIMS_TIME_ZONE_ID = "timeZoneId";
    public static final String ACCOUNT_ID = "accountId";
    public static final String AUTH_USER_INFO_ATTRIBUTE_NAME = "manager.auth_user_info";
    public static final String LOCALE_JWT_ATTRIBUTE_NAME = "manager.locale";
    public static final String TIMEZONE_JWT_ATTRIBUTE_NAME = "manager.timezone";
}
