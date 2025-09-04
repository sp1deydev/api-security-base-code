package com.thientdk.be_auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login_attempts")
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "attempt_time")
    private LocalDateTime attemptTime;

    @Column(name = "success")
    private Boolean success;

    @Column(name = "failure_reason")
    private String failureReason;

    @PrePersist
    public void prePersist() {
        if (attemptTime == null) {
            attemptTime = LocalDateTime.now();
        }
    }
}
