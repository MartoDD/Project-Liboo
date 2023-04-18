package com.example.projectliboo.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class BookEditDto {

    @NotBlank(message = "Book title cannot be blank!")
    private String title;
    private Long id;
    private int bookPages;
    private String bookDescription;
    private String bookCover;

    public BookEditDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
