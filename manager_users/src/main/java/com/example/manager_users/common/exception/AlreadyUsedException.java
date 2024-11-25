package com.example.manager_users.common.exception;

import com.example.manager_users.common.constant.ErrorCode;
import org.springframework.http.HttpStatus;

public class AlreadyUsedException extends ApplicationException {
    public AlreadyUsedException(String message) {
        super(ErrorCode.ALREADY_USED_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

    public AlreadyUsedException(Throwable cause) {
        super(ErrorCode.ALREADY_USED_EXCEPTION, HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), cause);
    }
}
