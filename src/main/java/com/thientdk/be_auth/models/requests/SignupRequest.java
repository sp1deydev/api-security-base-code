package com.thientdk.be_auth.models.requests;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;
    private String password;
    private String email;
}
