package com.thientdk.be_auth.services;

import com.thientdk.be_auth.aop.exceptions.ApiException;
import com.thientdk.be_auth.aop.exceptions.ErrorCode;
import com.thientdk.be_auth.entities.UserEntity;
import com.thientdk.be_auth.enums.Role;
import com.thientdk.be_auth.models.responses.UserResponse;
import com.thientdk.be_auth.repositories.UserRepository;
import com.thientdk.be_auth.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getUser(String id) {

        //validate input
        if (StringUtil.isNullOrEmpty(id)) {
            log.info("[getUser] - get user ERROR - is null or empty id");
            throw new ApiException(ErrorCode.BAD_REQUEST, "User id is not valid!");
        }

        log.info("[getUser] - get user with id: {} START", id);
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, "User not found!"));

        UserResponse userResponse = UserResponse.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .role(Role.getRole(userEntity.getRole()).getRole())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .createdBy(userEntity.getCreatedBy())
                .updatedBy(userEntity.getUpdatedBy())
                .build();

        log.info("[getUser] - get user with id: {} DONE", id);
        return userResponse;
    }

    public void updateUser() {
        log.info("[updateUser] - update user START");


        log.info("[updateUser] - update user DONE");
    }

    public void deleteUser() {
        log.info("[deleteUser] - delete user START");


        log.info("[deleteUser] - delete user DONE");
    }
}
