package com.edusantanaw.user.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserDTO {

    @NotBlank()
    @NotNull()
    public String name;

    @NotBlank()
    @Email()
    @NotNull()
    public String email;

    @NotBlank()
    @NotNull()
    public String password;
}
