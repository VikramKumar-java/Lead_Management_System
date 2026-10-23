package com.swan.lead_management_system.dto;

public record AuthResponse(
        String token,

        String tokenType,

        Long userId,

        String name,

        String email
) {
}
