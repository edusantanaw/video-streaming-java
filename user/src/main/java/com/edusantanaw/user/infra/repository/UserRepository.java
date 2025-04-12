package com.edusantanaw.user.infra.repository;

import com.edusantanaw.user.infra.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    public Optional<UserEntity> findByEmail(String email);
}
