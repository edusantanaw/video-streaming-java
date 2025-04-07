package com.edusantanaw.user.controllers.dtos;

import java.util.UUID;

public record CreateUserResponse (UUID id, String name, String email) {
}
