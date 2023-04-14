package com.example.projectliboo.service.Book;

import com.example.projectliboo.model.dtos.BookCreateDto;
import com.example.projectliboo.model.entity.Book;

import java.util.Optional;

public interface BookService {

    Optional<Book>findBookByTitle(String title);
    void addBook(BookCreateDto bookCreateDto);

}
