package ru.netology.autorization_service.model;

public class UserLogin {
    private String user;
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
