package com.edusantanaw.user.controllers.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDTO {
    @Email()
    @NotBlank()
    public String email;
    @NotBlank()
    public String password;
}
