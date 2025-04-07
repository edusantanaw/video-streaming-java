package com.edusantanaw.user.services;

import com.edusantanaw.user.controllers.dtos.CreateUserDTO;
import com.edusantanaw.user.domain.exceptions.DomainValidation;
import com.edusantanaw.user.infra.entities.UserEntity;
import com.edusantanaw.user.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class CreateUserService {
    protected UserRepository  userRepository;

    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity create(CreateUserDTO data) throws Exception {
        Optional<UserEntity> emailAlreadyUsed = this.userRepository.findByEmail(data.email);
        if(emailAlreadyUsed.isPresent()) throw new DomainValidation("E-mail já em uso!");
        UserEntity user = new UserEntity(data.name, data.email, data.password);
        this.userRepository.save(user);
        return user;
    }
}
