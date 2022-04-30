package com.appdev.api.usersservice.ui.controllers;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import com.appdev.api.usersservice.service.protocols.UserService;
import com.appdev.api.usersservice.shared.UserDto;
import com.appdev.api.usersservice.ui.model.CreateUserResponseModel;
import com.appdev.api.usersservice.ui.model.CreateUsersRequestModel;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "Users Service working on Port " + env.getProperty("local.server.port");
    }

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUsersRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        Configuration modelMapperConfig = modelMapper.getConfiguration();
        modelMapperConfig.setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);

        CreateUserResponseModel responseBody = modelMapper.map(createdUser, CreateUserResponseModel.class);

        ResponseEntity<CreateUserResponseModel> httpResponse = ResponseEntity.status(
                HttpStatus.CREATED).body(responseBody);

        return httpResponse;
    }

}
