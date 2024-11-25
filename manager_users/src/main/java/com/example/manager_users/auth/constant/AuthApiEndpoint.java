package com.example.manager_users.auth.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.example.manager_users.common.constant.CommonApiEndpoint.BASE_API;
import static com.example.manager_users.common.constant.CommonApiEndpoint.PRODUCT;
import static com.example.manager_users.common.constant.CommonApiEndpoint.VERSION;

@NoArgsConstructor(access = AccessLevel.NONE)
public class AuthApiEndpoint {
    public static final String AUTH_ENDPOINT = BASE_API + PRODUCT + "/auth" + VERSION;
}
