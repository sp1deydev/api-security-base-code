package com.thientdk.be_auth.services;

import com.thientdk.be_auth.aop.exceptions.ApiException;
import com.thientdk.be_auth.aop.exceptions.ErrorCode;
import com.thientdk.be_auth.configs.securities.JwtUtils;
import com.thientdk.be_auth.entities.UserEntity;
import com.thientdk.be_auth.enums.Role;
import com.thientdk.be_auth.models.requests.LoginRequest;
import com.thientdk.be_auth.models.requests.SignupRequest;
import com.thientdk.be_auth.models.responses.TextResponse;
import com.thientdk.be_auth.models.responses.UserResponse;
import com.thientdk.be_auth.repositories.UserRepository;
import com.thientdk.be_auth.utils.DataUtils;
import com.thientdk.be_auth.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public TextResponse login(LoginRequest request) {

        //validate request
        validateLoginRequest(request);

        log.info("[login] - login START with username: {}", request.getUsername());
        UserEntity entity = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> {
                    log.info("[login] - user not found ERROR");
                    return new ApiException(ErrorCode.BAD_REQUEST, "User not found!");
                });

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", entity.getId());
        extraClaims.put("authorities", userDetails.getAuthorities());
        String jwt = jwtUtils.generateToken(extraClaims, userDetails);

        log.info("[login] - login DONE");
        return new TextResponse(jwt);
    }

    public UserResponse signup(SignupRequest request) {

        //validate request
        this.validateSignupRequest(request);

        log.info("[signup] - Signup START with username: {}", request.getUsername());

        Optional<UserEntity> entity = userRepository.findByUsername(request.getUsername());

        if (entity.isPresent()) {
            log.info("[signup] - User is existed ERROR with username: {}", request.getUsername());
            throw new ApiException(ErrorCode.BAD_REQUEST, "User is existed!");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.USER.getId());
        UserEntity savedUser = userRepository.save(newUser);

        UserResponse response = UserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(Role.USER.getRole())
                .createdAt(savedUser.getCreatedAt())
                .createdBy(savedUser.getCreatedBy())
                .updatedAt(savedUser.getUpdatedAt())
                .updatedBy(savedUser.getUpdatedBy())
                .build();

        log.info("[signup] - Signup DONE");
        return response;
    }

    public void validateLoginRequest(LoginRequest request) {

        if (StringUtil.isNullOrEmpty(request.getUsername())) {
            log.info("[validateLoginRequest] - username is invalid ERROR");
            throw new ApiException(ErrorCode.BAD_REQUEST, "Username is invalid!");
        }
        if (StringUtil.isNullOrEmpty(request.getPassword()) || !DataUtils.isSecurePassword(request.getPassword())) {
            log.info("[validateLoginRequest] - password is invalid ERROR");
            throw new ApiException(ErrorCode.BAD_REQUEST, "Password is invalid!");
        }
    }

    public void validateSignupRequest(SignupRequest request) {

        if (StringUtil.isNullOrEmpty(request.getUsername())) {
            log.info("[validateSignupRequest] - username is invalid ERROR");
            throw new ApiException(ErrorCode.BAD_REQUEST, "Username is invalid!");
        }
        if (StringUtil.isNullOrEmpty(request.getPassword()) || !DataUtils.isSecurePassword(request.getPassword())) {
            log.info("[validateSignupRequest] - password is invalid ERROR");
            throw new ApiException(ErrorCode.BAD_REQUEST, "Password is invalid!");
        }
        if (StringUtil.isNullOrEmpty(request.getEmail()) || !DataUtils.isEmail(request.getEmail())) {
            log.info("[validateSignupRequest] - email is invalid ERROR");
            throw new ApiException(ErrorCode.BAD_REQUEST, "Email is invalid!");
        }
    }
}
