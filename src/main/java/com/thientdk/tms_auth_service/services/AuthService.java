package com.thientdk.tms_auth_service.services;

import com.thientdk.tms_auth_service.aop.exceptions.ApiException;
import com.thientdk.tms_auth_service.aop.exceptions.ErrorCode;
import com.thientdk.tms_auth_service.configs.securities.JwtUtils;
import com.thientdk.tms_auth_service.entities.UserEntity;
import com.thientdk.tms_auth_service.models.requests.LoginRequest;
import com.thientdk.tms_auth_service.models.responses.TextResponse;
import com.thientdk.tms_auth_service.repositories.UserRepository;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public TextResponse login(LoginRequest loginRequest){

        log.info("[login] - login START with username {}", loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserEntity entity = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(()-> {
                    log.info("[login] - user not found ERROR");
                    return new ApiException(ErrorCode.BAD_REQUEST, "User not found!");
                });
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", entity.getId());
        extraClaims.put("authorities",userDetails.getAuthorities());
        String jwt = jwtUtils.generateToken(extraClaims, userDetails);
        log.info("[login] - login DONE");
        return new TextResponse(jwt);
    }

    public void signup() {

    }
}
