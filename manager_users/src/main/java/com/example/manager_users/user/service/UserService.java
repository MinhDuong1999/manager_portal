package com.example.manager_users.user.service;

import com.example.manager_users.common.dto.response.CommonResponse;
import com.example.manager_users.user.dto.request.CreateUser;
import com.example.manager_users.user.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getUsers();

    CommonResponse<Boolean> createUser(CreateUser createUser);
}

