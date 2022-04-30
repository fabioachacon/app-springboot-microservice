package com.appdev.api.usersservice.persistence.protocols;

import com.appdev.api.usersservice.persistence.UserEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
