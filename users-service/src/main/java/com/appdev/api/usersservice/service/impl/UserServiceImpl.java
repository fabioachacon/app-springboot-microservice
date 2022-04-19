package com.appdev.api.usersservice.service.impl;

import java.util.UUID;

import com.appdev.api.usersservice.data.UserEntity;
import com.appdev.api.usersservice.data.protocols.UserRepository;
import com.appdev.api.usersservice.service.protocols.UserService;
import com.appdev.api.usersservice.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        String userId = getUserUUID();
        userDetails.setUserId(userId);

        ModelMapper modelMapper = new ModelMapper();
        Configuration modelMapperConfig = modelMapper.getConfiguration();
        modelMapperConfig.setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setEncryptedPassword("test");
        userRepository.save(userEntity);

        return null;
    }

    private String getUserUUID() {
        UUID userUUID = UUID.randomUUID();
        String userId = userUUID.toString();

        return userId;
    }

}
