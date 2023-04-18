package com.example.projectliboo.model.view;

import com.example.projectliboo.model.entity.Author;

import java.util.List;

public class AuthorListView {

    private List<Author>allAuthors;

    public AuthorListView() {
    }

    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    public void setAllAuthors(List<Author> allAuthors) {
        this.allAuthors = allAuthors;
    }
}
