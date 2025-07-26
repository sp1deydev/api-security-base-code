package com.thientdk.be_auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER(1, "USER"),
    ADMIN(2, "ADMIN");

    private final int id;
    private final String role;

    public static Role getRole(int id) {
        for (Role role : Role.values()) {
            if (role.id == id) {
                return role;
            }
        }
        throw new IllegalArgumentException("No Role with id = " + id);
    }
}
