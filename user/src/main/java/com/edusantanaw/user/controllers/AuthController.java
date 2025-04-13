package com.edusantanaw.user.controllers;

import com.edusantanaw.user.controllers.dtos.AuthDTO;
import com.edusantanaw.user.domain.dto.AuthResponseDTO;
import com.edusantanaw.user.domain.exceptions.DomainValidation;
import com.edusantanaw.user.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthResponseDTO> auth(@RequestBody  @Valid AuthDTO data) throws DomainValidation {
        AuthResponseDTO token = this.authService.auth(data);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
