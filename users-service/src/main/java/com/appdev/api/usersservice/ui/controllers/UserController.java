package com.appdev.api.usersservice.ui.controllers;

import org.springframework.core.env.Environment;

import javax.validation.Valid;

import com.appdev.api.usersservice.ui.model.CreateUsersRequestModel;

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

    @GetMapping("/status/check")
    public String status() {
        return "Users Service working on Port " + env.getProperty("local.server.port");
    }

    @PostMapping
    public String createUser(@Valid @RequestBody CreateUsersRequestModel userDetails) {
        return "Create user method is called";
    }

}
