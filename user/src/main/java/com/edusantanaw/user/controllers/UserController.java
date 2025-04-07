package com.edusantanaw.user.controllers;

import com.edusantanaw.user.controllers.dtos.CreateUserDTO;
import com.edusantanaw.user.controllers.dtos.CreateUserResponse;
import com.edusantanaw.user.infra.entities.UserEntity;
import com.edusantanaw.user.services.CreateUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/users")
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserService createUserService;

    public UserController(CreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> create(@RequestBody @Valid CreateUserDTO data) throws Exception {
        UserEntity user = this.createUserService.create(data);
        CreateUserResponse createUserResponse = new CreateUserResponse(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponse);
    }
}
