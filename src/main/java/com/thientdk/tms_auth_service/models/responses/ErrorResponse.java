package com.thientdk.tms_auth_service.models.responses;

import com.thientdk.tms_auth_service.aop.exceptions.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int code;
    private String message;
    private boolean success;
    private String error;
    private String path;

    public static ErrorResponse of(ErrorCode errorCode, String path) {
        return new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.isSuccess(),
                errorCode.name(),
                path
        );
    }
}
