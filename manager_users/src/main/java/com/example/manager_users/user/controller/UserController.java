package com.example.manager_users.user.controller;

import com.example.manager_users.common.dto.response.CommonResponse;
import com.example.manager_users.user.dto.request.CreateUser;
import com.example.manager_users.user.dto.response.UserResponse;
import com.example.manager_users.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.manager_users.common.constant.CommonApiEndpoint.BASE_API;
import static com.example.manager_users.common.constant.CommonApiEndpoint.PRODUCT;
import static com.example.manager_users.common.constant.CommonApiEndpoint.USERS;
import static com.example.manager_users.common.constant.CommonApiEndpoint.VERSION;

@RestController
@RequestMapping(BASE_API + PRODUCT + USERS + VERSION)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public CommonResponse<List<UserResponse>> getUsers() {
        return CommonResponse.createSuccessData(userService.getUsers());
    }

    @PostMapping
    public CommonResponse<Boolean> createUser(@RequestBody @Valid CreateUser request) {
        return userService.createUser(request);
    }
}
