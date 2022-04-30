package com.appdev.api.usersservice.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import com.appdev.api.usersservice.persistence.UserEntity;
import com.appdev.api.usersservice.persistence.protocols.UserRepository;
import com.appdev.api.usersservice.service.protocols.UserService;
import com.appdev.api.usersservice.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private String getUserUUID() {
        UUID userUUID = UUID.randomUUID();
        String userId = userUUID.toString();

        return userId;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        String userId = getUserUUID();
        userDetails.setUserId(userId);

        String userPassword = userDetails.getPassword();
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userPassword));

        ModelMapper modelMapper = new ModelMapper();
        Configuration modelMapperConfig = modelMapper.getConfiguration();
        modelMapperConfig.setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userRepository.save(userEntity);

        UserDto createdUser = modelMapper.map(userEntity, UserDto.class);

        return createdUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        String userEmail = userEntity.getEmail();
        String userPassword = userEntity.getEncryptedPassword();

        return new User(userEmail,
                userPassword,
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new ModelMapper().map(userEntity, UserDto.class);
    }

}
