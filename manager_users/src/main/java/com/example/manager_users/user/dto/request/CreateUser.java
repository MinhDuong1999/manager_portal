package com.example.manager_users.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {
    private String username;
    private String password;
}
