package com.thientdk.be_auth.models.responses;

import com.thientdk.be_auth.aop.exceptions.ErrorCode;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int code;
    private String message;
    private boolean success;
    private String error;
    private String path;
    private String method;

    public static ErrorResponse of(ErrorCode errorCode, String path, String method) {
        return new ErrorResponse(
                LocalDateTime.now(),
                errorCode.getCode(),
                errorCode.getMessage(),
                errorCode.isSuccess(),
                errorCode.name(),
                path,
                method
        );
    }
}
