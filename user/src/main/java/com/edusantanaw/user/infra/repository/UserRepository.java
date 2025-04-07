package com.edusantanaw.user.infra.repository;

import com.edusantanaw.user.infra.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    public Optional<UserEntity> findByEmail(String email);
}
