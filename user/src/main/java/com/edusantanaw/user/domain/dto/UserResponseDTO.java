package com.edusantanaw.user.domain.dto;

import java.util.UUID;

public record NewUserResponseDTO(UUID id, String name, String email) {
}
