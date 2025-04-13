package com.edusantanaw.user.services;

import com.edusantanaw.user.domain.dto.UserResponseDTO;
import com.edusantanaw.user.domain.exceptions.NotFoundException;
import com.edusantanaw.user.infra.entities.UserEntity;
import com.edusantanaw.user.infra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoadUserByIdService {
    protected UserRepository userRepository;

    public LoadUserByIdService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO exec(UUID id) throws NotFoundException {
        Optional<UserEntity> userExists = this.userRepository.findById(id);
        if (userExists.isEmpty()) throw new NotFoundException("User not found!");
        UserEntity userEntity = userExists.get();
        return new UserResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
    }
}
