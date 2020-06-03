package com.example.user.dto;

public class CreateUserRequestDTO {
    private String username;
    private String password;

    public CreateUserRequestDTO() {
    }

    public CreateUserRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
