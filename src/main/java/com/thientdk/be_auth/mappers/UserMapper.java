package com.thientdk.be_auth.mappers;

import com.thientdk.be_auth.entities.UserEntity;
import com.thientdk.be_auth.enums.Role;
import com.thientdk.be_auth.models.responses.UserResponse;

public class UserMapper {
    public static UserResponse fromUserEntityToUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .role(Role.getRole(userEntity.getRole()).getRole())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .createdBy(userEntity.getCreatedBy())
                .updatedBy(userEntity.getUpdatedBy())
                .build();
    }
}
