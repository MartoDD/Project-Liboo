package com.example.projectliboo.service.Author;

import com.example.projectliboo.model.dtos.AuthorCreateDto;
import com.example.projectliboo.model.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void addAuthor(AuthorCreateDto authorCreateDto);

    List<Author> getAllAuthors();

    Optional<Author> findAuthorByName(String name);

}
