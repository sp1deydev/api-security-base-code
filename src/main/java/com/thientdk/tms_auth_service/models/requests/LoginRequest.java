package com.thientdk.tms_auth_service.models.requests;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
