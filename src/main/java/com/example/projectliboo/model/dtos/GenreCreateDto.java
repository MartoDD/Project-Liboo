package com.example.projectliboo.model.dtos;

import com.example.projectliboo.validation.annotations.UniqueGenreName;
import jakarta.validation.constraints.NotBlank;

public class GenreCreateDto {
    @NotBlank(message = "Name cannot be blank!")
    @UniqueGenreName
    private String genreName;

    public GenreCreateDto() {
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
