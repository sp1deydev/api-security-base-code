package com.thientdk.be_auth.controllers;

import com.thientdk.be_auth.models.requests.LoginRequest;
import com.thientdk.be_auth.models.requests.SignupRequest;
import com.thientdk.be_auth.models.responses.TextResponse;
import com.thientdk.be_auth.models.responses.UserResponse;
import com.thientdk.be_auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TextResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }


    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }
}
