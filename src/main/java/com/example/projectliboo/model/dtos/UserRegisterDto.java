package com.example.projectliboo.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.example.projectliboo.validation.annotations.UniqueEmail;
import com.example.projectliboo.validation.annotations.UniqueUsername;

public class UserRegisterDto {



    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @UniqueUsername
    @NotBlank(message = "Username cannot be blank!")
    private String username;
    @Email(message = "Enter a valid email!")
    @UniqueEmail
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @NotBlank(message = "Password cannot be blank!")
    @Size(min = 3, max = 20,message = "Password length must be between 3 and 20 characters!")
    private String password;
    private String confirmPassword;

    public UserRegisterDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
