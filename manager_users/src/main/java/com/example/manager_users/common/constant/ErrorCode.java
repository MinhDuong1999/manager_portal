package com.example.manager_users.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCode {
    public static final Integer FORBIDDEN_EXCEPTION = 4000;
    public static final Integer OLD_PASSWORD_NOT_MATCH = 4003;
    public static final Integer DUPLICATE_OLD_PASSWORD = 4004;
    public static final Integer INPUT_DATA_INVALID = 4005;
    public static final Integer AUTHENTICATION_EXCEPTION = 4001;
    public static final Integer NOT_ACCEPT_TERM_EXCEPTION = 4010;
    public static final Integer NOT_ACCEPT_EMAIL = 4011;
    public static final Integer ACCESS_DENIED_EXCEPTION = 4002;
    public static final Integer USER_INACTIVE_EXCEPTION = 4021;
    public static final Integer USER_LOCKED_EXCEPTION = 4022;
    public static final Integer RESOURCE_LOCKED_EXCEPTION = 4022;
    public static final Integer NOT_FOUND_CONFIRMATION = 4041;

    public static final Integer USER_NAME_NOT_FOUND = 4042;

    public static final Integer MISSING_REQUIRED_FIELD = 5003;
    public static final Integer APPLICATION_EXCEPTION = 5000;
    public static final Integer EXECUTION_EXCEPTION = 5002;
    public static final Integer LOAD_MAPPER_EXCEPTION = 5001;
    public static final Integer PASSWORD_VALIDATION_FAILED = 5005;
    public static final Integer NO_DATA = 5008;
    public static final Integer DUPLICATE_EXCEPTION = 5009;
    public static final Integer NOT_ALLOW_CHANGE_EXCEPTION = 5010;
    public static final Integer EMAIL_EXCEPTION = 5011;
    public static final Integer SELLER_EXCEED_EXCEPTION = 5012;
    public static final Integer STORAGE_EXCEPTION = 5013;
    public static final Integer NOT_IMPLEMENTED = 5014;
    public static final Integer IMPORT_DATA_FAILED = 5015;
    public static final Integer ALREADY_USED_EXCEPTION = 5016;
}
