package com.thientdk.be_auth.services;

import com.thientdk.be_auth.aop.exceptions.ApiException;
import com.thientdk.be_auth.aop.exceptions.ErrorCode;
import com.thientdk.be_auth.entities.UserEntity;
import com.thientdk.be_auth.mappers.UserMapper;
import com.thientdk.be_auth.models.responses.UserResponse;
import com.thientdk.be_auth.repositories.UserRepository;
import com.thientdk.be_auth.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> {
                    log.info("[getUser] - get user ERROR - user not found");
                    return new ApiException(ErrorCode.BAD_REQUEST, "User not found!");
                });

        log.info("[getUser] - get user with id: {} DONE", id);
        return UserMapper.fromUserEntityToUserResponse(userEntity);
    }

    public Page<UserResponse> getUsers(Integer page, Integer size, String keySearch, Pageable pageable) {
        log.info("[getUsers] - get users list START");

        Sort sort = pageable.getSort();
        Pageable pageRequest = PageRequest.of(page, size, sort);
        Page<UserEntity> users;

        users = userRepository.searchUsers(keySearch, pageRequest);

        List<UserResponse> userResponses = users.getContent().stream().map(UserMapper::fromUserEntityToUserResponse).toList();

        log.info("[getUsers] - get users list DONE");
        return new PageImpl<>(userResponses, pageRequest, users.getTotalElements());
    }

    public void updateUser() {
        log.info("[updateUser] - update user START");


        log.info("[updateUser] - update user DONE");
    }

    public void deleteUser() {
        log.info("[deleteUser] - delete user START");


        log.info("[deleteUser] - delete user DONE");
    }

    public void activeUser(String userId) {
        log.info("[activeUser] - active user START");

        log.info("[activeUser] - active user DONE");
    }

    public void disableUser(String userId) {
        log.info("[disableUser] - disable user START");

        log.info("[disableUser] - disable user DONE");
    }
}
