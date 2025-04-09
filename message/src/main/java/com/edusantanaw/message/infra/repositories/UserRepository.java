package com.edusantanaw.message.infra.repositories;

import com.edusantanaw.message.infra.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
