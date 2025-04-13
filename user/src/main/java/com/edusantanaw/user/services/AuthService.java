package com.edusantanaw.user.services;

import com.edusantanaw.user.config.security.UserDetailsImpl;
import com.edusantanaw.user.controllers.dtos.AuthDTO;
import com.edusantanaw.user.domain.dto.AuthResponseDTO;
import com.edusantanaw.user.domain.exceptions.DomainValidation;
import com.edusantanaw.user.infra.entities.UserEntity;
import com.edusantanaw.user.infra.repository.UserRepository;
import com.edusantanaw.user.config.security.JWTService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    protected UserRepository userRepository;
    protected PasswordEncoder passwordEncoder;
    protected JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponseDTO auth(AuthDTO data) throws DomainValidation {
        Optional<UserEntity> userExists = this.userRepository.findByEmail(data.email);
        if (userExists.isEmpty()) throw new DomainValidation("E-mail or password is invalid!");
        UserEntity user = userExists.get();
        boolean passwordIsValid = this.passwordEncoder.matches(data.password, user.getPassword());
        if (!passwordIsValid) throw new DomainValidation("E-mail or password is invalid!");
        UserDetailsImpl userDetails = new UserDetailsImpl(user.getName(), user.getEmail());
        String token = this.jwtService.generate(userDetails);
        return new AuthResponseDTO(token);
    }

}
