package com.example.projectliboo.model.dtos;

import com.example.projectliboo.validation.annotations.UniqueBookName;
import jakarta.validation.constraints.NotBlank;

public class BookCreateDto {

    @NotBlank(message = "Title cannot be blank!")
    @UniqueBookName
    private String title;
    private String description;
    private String imageUrl;
    private String authorName;
    private int numberOfPages;
    private String genre;

    public BookCreateDto() {
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
