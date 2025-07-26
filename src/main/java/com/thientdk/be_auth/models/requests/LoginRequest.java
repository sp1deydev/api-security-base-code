package com.thientdk.be_auth.models.requests;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
