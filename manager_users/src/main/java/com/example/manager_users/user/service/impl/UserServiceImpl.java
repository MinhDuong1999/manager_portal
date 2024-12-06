package com.example.manager_users.user.service.impl;

import com.example.manager_users.common.dto.response.CommonResponse;
import com.example.manager_users.common.dto.response.MessageResponse;
import com.example.manager_users.common.entity.UserEntity;
import com.example.manager_users.common.enums.MessageResource;
import com.example.manager_users.common.enums.Role;
import com.example.manager_users.common.exception.AlreadyUsedException;
import com.example.manager_users.common.repository.UserRepository;
import com.example.manager_users.common.repository.httpclient.ProfileClient;
import com.example.manager_users.user.dto.request.CreateUser;
import com.example.manager_users.user.dto.response.UserResponse;
import com.example.manager_users.user.mapper.UserMapper;
import com.example.manager_users.user.service.UserService;
import com.example.manager_users.common.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.example.manager_users.auth.constant.DataConstant.LOCAL;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final MessageHelper messageHelper;
    private final ProfileClient profileClient;

    @Override
    public List<UserResponse> getUsers() {
        return userRepository.findAllByIsActiveIsTrue().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public CommonResponse<Boolean> createUser(CreateUser createUser) {
        if (userRepository.existsByUserNameAndProvider(createUser.getUsername(), LOCAL)) {
            throw new AlreadyUsedException(messageHelper.getMessage(MessageResource.ALREADY_IN_USE.getCode(), new String[]{createUser.getUsername()}, Locale.ENGLISH));
        }
        UserEntity addUser = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .userName(createUser.getUsername())
                .password(passwordEncoder.encode(createUser.getPassword()))
                .provider(LOCAL)
                .isActive(Boolean.TRUE)
                .role(Role.EMPLOYEE.name())
                .build();
        userRepository.save(addUser);

        var addProfile = userMapper.toProfileRequest(createUser);
        addProfile.setUserId(addUser.getId());
        profileClient.createProfile(addProfile);

        return CommonResponse.createSuccessData(true,
                MessageResponse.builder()
                        .description(messageHelper.getMessage(MessageResource.SAVE_SUCCESSFULLY.getCode(), Locale.ENGLISH))
                        .build());
    }

    @Override
    public CommonResponse<Boolean> deleteUsers(String id) {
        userRepository.deleteById(id);
        return CommonResponse.createSuccessData(true,
                MessageResponse.builder()
                        .description(messageHelper.getMessage(MessageResource.SAVE_SUCCESSFULLY.getCode(), Locale.ENGLISH))
                        .build());
    }
}
