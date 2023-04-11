package com.example.projectliboo.service.Author;

import com.example.projectliboo.model.dtos.AuthorCreateDto;

import java.util.List;

public interface AuthorService {

    void addAuthor(AuthorCreateDto authorCreateDto);
    AuthorCreateDto getAllAuthors();

}
