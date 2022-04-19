package com.appdev.api.usersservice.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUsersRequestModel {

    @NotNull(message = "First Name must be provided")
    @Size(min = 2, message = "First Name name must not be less than 2 characters")
    private String firstName;

    @NotNull(message = "Last Name must be provided")
    @Size(min = 2, message = "Last Name name must not be less than 2 characters")
    private String lastName;

    @NotNull(message = "A password must be provided")
    @Size(min = 8, max = 16, message = "Password must be equal or grater than 8 characters and less than 16 characters")
    private String password;

    @NotNull(message = "Email must be provided")
    @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
