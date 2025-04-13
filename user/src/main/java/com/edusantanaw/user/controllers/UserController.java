package com.edusantanaw.user.controllers;

import com.edusantanaw.user.controllers.dtos.CreateUserDTO;
import com.edusantanaw.user.domain.dto.UserResponseDTO;
import com.edusantanaw.user.domain.exceptions.NotFoundException;
import com.edusantanaw.user.services.CreateUserService;
import com.edusantanaw.user.services.LoadUserByIdService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserService createUserService;
    private final LoadUserByIdService loadUserByIdService;

    public UserController(CreateUserService createUserService, LoadUserByIdService loadUserByIdService) {
        this.createUserService = createUserService;
        this.loadUserByIdService = loadUserByIdService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid CreateUserDTO data) throws Exception {
        UserResponseDTO user = this.createUserService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("{id}")
    @RequestMapping("{id}")
    public  ResponseEntity<UserResponseDTO> loadById(@PathVariable UUID id) throws NotFoundException {
        System.out.println(id);
        UserResponseDTO user = this.loadUserByIdService.exec(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
