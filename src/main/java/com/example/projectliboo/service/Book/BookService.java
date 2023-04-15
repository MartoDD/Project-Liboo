package com.example.projectliboo.service.Book;

import com.example.projectliboo.model.dtos.BookCreateDto;
import com.example.projectliboo.model.entity.Book;
import com.example.projectliboo.model.view.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {

    Optional<Book> findBookByTitle(String title);

    void addBook(BookCreateDto bookCreateDto);

    BookView getBookById(Long id);

    Page<BookView> getAllBooks(Pageable pageable);

    BookView map(Book book);

}
