package com.example.manager_users.common.exception;

import com.example.manager_users.common.constant.ErrorCode;
import com.example.manager_users.common.dto.response.CommonResponse;
import com.example.manager_users.common.dto.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<CommonResponse<Object>> userNotFound(UsernameNotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                .body(CommonResponse.builder().isSuccess(true)
                        .message(MessageResponse.builder()
                                .code(String.valueOf(ErrorCode.USER_NAME_NOT_FOUND))
                                .description(exception.getMessage()).build())
                        .build());

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CommonResponse<Object>> accessDenied(AccessDeniedException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(CommonResponse.builder().isSuccess(false)
                        .message(MessageResponse.builder().code(String.valueOf(ErrorCode.ACCESS_DENIED_EXCEPTION)).description(exception.getMessage()).build()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Object>> handleException(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.internalServerError()
                .body(CommonResponse.builder().isSuccess(false)
                        .message(MessageResponse.builder().description(exception.getMessage()).code(String.valueOf(ErrorCode.APPLICATION_EXCEPTION)).build()).build());
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<CommonResponse<Object>> handleApplicationException(ApplicationException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode())
                .body(CommonResponse.builder()
                        .isSuccess(false)
                        .message(MessageResponse.builder()
                                .description(exception.getErrorDetail().errorMessage())
                                .code(String.valueOf(exception.getErrorDetail().errorCode()))
                                .build())
                        .build());
    }
}
