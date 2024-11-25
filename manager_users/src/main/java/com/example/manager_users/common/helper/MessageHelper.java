package com.example.manager_users.common.helper;

import com.example.manager_users.common.enums.MessageResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageHelper {
    private static final String NOT_FOUND_ERROR = " not found in message source.";
    private final MessageSource messageSource;

    public String getMessage(String code, Locale locale) {
        try {
            return messageSource.getMessage(code, null, locale);
        } catch (Exception e) {
            return code + NOT_FOUND_ERROR;
        }
    }

    public String getMessage(String code, String[] params, Locale locale) {
        try {
            return messageSource.getMessage(code, params, locale);
        } catch (Exception e) {
            return code + NOT_FOUND_ERROR;
        }
    }

    public String getMessage(MessageResource codeResource, Locale locale, String... args) {
        try {
            return messageSource.getMessage(codeResource.getCode(), args, locale);
        } catch (Exception e) {
            return codeResource.getCode() + NOT_FOUND_ERROR;
        }
    }
}
