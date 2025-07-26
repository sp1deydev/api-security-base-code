package com.thientdk.be_auth.aop.exceptions;

import com.thientdk.be_auth.models.responses.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    ResponseEntity<ErrorResponse> handlingAppException(ApiException exception, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMethod(request.getMethod());
        errorResponse.setError(exception.getMessage());
        errorResponse.setCode(exception.getErrorCode().getCode());
        errorResponse.setMessage(exception.getExceptionMessage());

        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(errorResponse.getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
        ErrorCode fallback = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.of(fallback, request.getRequestURI(), request.getMethod());
        return ResponseEntity.status(fallback.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception,
                                                                       HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(java.time.LocalDateTime.now());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMethod(request.getMethod());
        errorResponse.setError(HttpStatus.UNAUTHORIZED.name());
        errorResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
