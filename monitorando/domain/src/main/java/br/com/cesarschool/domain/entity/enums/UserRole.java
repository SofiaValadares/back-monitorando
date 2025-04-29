package br.com.cesarschool.domain.entity.enums;

import java.util.Arrays;

public enum UserRole {
    STUDENT,
    MONITOR,
    PROFESSOR;

    public static UserRole fromString(String roleString) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.name().equalsIgnoreCase(roleString))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + roleString));
    }
}
