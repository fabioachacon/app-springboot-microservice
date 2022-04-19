package com.appdev.api.usersservice.service.protocols;

import com.appdev.api.usersservice.shared.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDetails);
}
