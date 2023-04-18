package com.example.projectliboo.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserEditDto {

    private String profilePicture;
    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "You must input a valid email!")
    private String email;
    @NotBlank(message = "Name cannot be blank!")
    private String fullName;

    public UserEditDto() {
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
