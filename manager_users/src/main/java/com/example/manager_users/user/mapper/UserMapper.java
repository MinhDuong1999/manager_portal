package com.example.manager_users.user.mapper;

import com.example.manager_users.common.entity.UserEntity;
import com.example.manager_users.user.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "accountId" , source = "id")
    UserResponse toUserResponse(UserEntity user);
}
