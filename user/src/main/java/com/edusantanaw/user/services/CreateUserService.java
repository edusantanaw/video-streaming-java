package com.edusantanaw.user.services;

import com.edusantanaw.user.controllers.dtos.CreateUserDTO;
import com.edusantanaw.user.domain.dto.NewUserResponseDTO;
import com.edusantanaw.user.domain.exceptions.DomainValidation;
import com.edusantanaw.user.infra.entities.UserEntity;
import com.edusantanaw.user.infra.producer.NewUserProducer;
import com.edusantanaw.user.infra.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class CreateUserService {
    protected UserRepository userRepository;
    protected PasswordEncoder passwordEncoder;
    protected NewUserProducer producer;

    public CreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder, NewUserProducer producer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.producer = producer;
    }

    public NewUserResponseDTO create(CreateUserDTO data) throws Exception {
        Optional<UserEntity> emailAlreadyUsed = this.userRepository.findByEmail(data.email);
        if (emailAlreadyUsed.isPresent()) throw new DomainValidation("E-mail já em uso!");
        String hashedPassword = this.passwordEncoder.encode(data.password);
        UserEntity user = new UserEntity(data.name, data.email, hashedPassword);
        this.userRepository.save(user);
        NewUserResponseDTO userResponse = new NewUserResponseDTO(user.getId(), user.getName(), user.getEmail());
        this.producer.exec(userResponse);
        return userResponse;
    }
}
