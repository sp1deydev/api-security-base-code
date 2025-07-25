package com.thientdk.tms_auth_service.aop.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    private ErrorCode errorCode;
    private String exceptionMessage;

    public ApiException(ErrorCode errorCode, String exceptionMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.exceptionMessage = exceptionMessage;
    }

    public ApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return errorCode.getMessage(exceptionMessage);
    }
}
