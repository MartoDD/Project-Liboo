package com.example.projectliboo.model.entity;


import com.example.projectliboo.model.enums.GenreEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genreName;

    @ManyToMany
    private List<Book> books;

    public Genre() {
    }

    public GenreEnum getGenreName() {
        return genreName;
    }

    public void setGenreName(GenreEnum genreName) {
        this.genreName = genreName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
