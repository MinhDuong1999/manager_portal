package com.example.manager_users.common.exception;

import com.example.manager_users.common.constant.ErrorCode;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends ApplicationException {
    public AuthenticationException() {
        super(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    public AuthenticationException(String message) {
        super(ErrorCode.AUTHENTICATION_EXCEPTION, HttpStatus.UNAUTHORIZED, message);
    }

    public AuthenticationException(Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase(), cause);
    }
}
