package com.thientdk.tms_auth_service.aop.exceptions;

import com.thientdk.tms_auth_service.models.responses.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    ResponseEntity<ErrorResponse> handlingAppException(ApiException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(exception.getErrorCode().getCode());
        errorResponse.setMessage(exception.getMessage());

        return ResponseEntity.status(exception.getErrorCode().getHttpStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
        ErrorCode fallback = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.of(fallback, request.getRequestURI());
        return ResponseEntity.status(fallback.getHttpStatusCode()).body(response);
    }
}
