package com.example.projectliboo.model.dtos;

import com.example.projectliboo.validation.annotations.UniqueName;
import jakarta.validation.constraints.NotBlank;

public class AuthorCreateDto {
    @NotBlank(message = "Author name cannot be blank")
    @UniqueName
    private String name;
    @NotBlank(message = "Information cannot be blank")
    private String information;
    private String profilePicture;

    public AuthorCreateDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
