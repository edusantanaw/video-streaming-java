package com.edusantanaw.user.domain.dto;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email) {
}
