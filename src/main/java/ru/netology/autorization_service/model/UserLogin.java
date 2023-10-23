package ru.netology.autorization_service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserLogin {
    @NotNull(message = "user can't be null")
    @NotBlank(message = "user can't be blank")
    private String user;
    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be blank")
    private String password;

    public UserLogin(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
