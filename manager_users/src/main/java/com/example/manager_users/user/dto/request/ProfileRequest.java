package com.example.manager_users.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String userId;
}
