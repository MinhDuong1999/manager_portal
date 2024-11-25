package com.example.manager_users.common.exception;

import com.example.manager_users.common.constant.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApplicationException extends RuntimeException {
    protected final transient ErrorDetails errorDetail;
    protected final transient HttpStatus statusCode;

    public ApplicationException(int errorCode, String responseMessage) {
        super(responseMessage);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorDetail = new ErrorDetails(errorCode, responseMessage);
    }

    public ApplicationException(int errorCode, String errorMessage, Throwable rootCause) {
        super(errorMessage, rootCause);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        this.errorDetail = new ErrorDetails(errorCode, errorMessage);
    }

    public ApplicationException(HttpStatus statusCode, String responseMessage) {
        super(responseMessage);
        this.statusCode = statusCode;
        this.errorDetail = new ErrorDetails(ErrorCode.APPLICATION_EXCEPTION, responseMessage);
    }

    public ApplicationException(int errorCode, HttpStatus statusCode, String responseMessage) {
        super(responseMessage);
        this.statusCode = statusCode;
        this.errorDetail = new ErrorDetails(errorCode, responseMessage);
    }

    public ApplicationException(HttpStatus statusCode, String errorMessage, Throwable rootCause) {
        super(errorMessage, rootCause);
        this.statusCode = statusCode;
        this.errorDetail = new ErrorDetails(ErrorCode.APPLICATION_EXCEPTION,errorMessage);
    }

    public ApplicationException(int errorCode, HttpStatus statusCode, String errorMessage, Throwable rootCause) {
        super(errorMessage, rootCause);
        this.statusCode = statusCode;
        this.errorDetail = new ErrorDetails(errorCode, errorMessage);
    }
}
