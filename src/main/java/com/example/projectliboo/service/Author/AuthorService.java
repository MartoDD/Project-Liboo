package com.example.projectliboo.service.Author;

import com.example.projectliboo.model.dtos.AuthorCreateDto;
import com.example.projectliboo.model.dtos.AuthorEditDto;
import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.model.view.AuthorView;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void addAuthor(AuthorCreateDto authorCreateDto);

    List<Author> getAllAuthors();

    Optional<Author> findAuthorByName(String name);

    AuthorView findAuthorById(Long id);

    void editAuthor(Long id, AuthorEditDto authorEditDto);

    List<AuthorView> getAllAuthors(String keyword);

    AuthorView map(Author author);
    AuthorEditDto getAuthorByIdForEdit(Long id);
    AuthorEditDto mapForEdit(Author author);

}
