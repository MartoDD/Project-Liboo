package com.example.projectliboo.model.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity {

    @Column(nullable = false)

    private String genreName;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;

    public Genre() {
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
