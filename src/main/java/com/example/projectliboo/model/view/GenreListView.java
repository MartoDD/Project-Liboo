package com.example.projectliboo.model.view;

import com.example.projectliboo.model.entity.Genre;

import java.util.List;

public class GenreListView {

    private List<Genre> genres;

    public GenreListView() {
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
