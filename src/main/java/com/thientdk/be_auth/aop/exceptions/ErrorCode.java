package com.thientdk.be_auth.aop.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    SUCCESS(1, "Success", true, HttpStatus.OK),
    INVALID_REQUEST(100, "Invalid request", false, HttpStatus.BAD_REQUEST),
    SYSTEM_ERROR(99, "The system is upgrading this feature, please try again later!", false, HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(400, "Bad request", false, HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(404, "Resource not found", false, HttpStatus.NOT_FOUND),
    DUPLICATE_RESOURCE(409, "Duplicate resource", false, HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR(500, "Internal server error", false, HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(401, "Unauthenticated: Access token is missing or invalid", false, HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(403, "You do not have permission to access this resource", false, HttpStatus.FORBIDDEN);

    private final int code;
    private final String message;
    private final boolean success;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, boolean success, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.httpStatusCode = httpStatusCode;
    }

    public String getMessage(String additionalMessage) {
        return String.format(message, additionalMessage);
    }

}
