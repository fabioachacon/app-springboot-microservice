package com.appdev.api.usersservice.data.protocols;

import com.appdev.api.usersservice.data.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
