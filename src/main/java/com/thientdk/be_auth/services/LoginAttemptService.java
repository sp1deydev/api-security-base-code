package com.thientdk.be_auth.services;

import com.thientdk.be_auth.entities.LoginAttempt;
import com.thientdk.be_auth.repositories.LoginAttemptRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginAttemptService {
    private final LoginAttemptRepository loginAttemptRepository;
    private final HttpServletRequest httpServletRequest;

    public void saveLoginAttempt(String userId, String username, boolean success, String failureReason) {
        log.info("[saveLoginAttempt] - save login log START");
        LoginAttempt attempt = LoginAttempt.builder()
                .userId(userId)
                .username(username)
                .ipAddress(getClientIp())
                .userAgent(getUserAgent())
                .success(success)
                .failureReason(failureReason)
                .build();

        loginAttemptRepository.save(attempt);
        log.info("[saveLoginAttempt] - save login log END");
    }

    private String getClientIp() {
        String xfHeader = httpServletRequest.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return httpServletRequest.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    private String getUserAgent() {
        return httpServletRequest.getHeader("User-Agent");
    }
}
