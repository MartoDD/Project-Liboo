package com.example.projectliboo.service.Book;

import com.example.projectliboo.model.dtos.BookCreateDto;
import com.example.projectliboo.model.dtos.BookEditDto;
import com.example.projectliboo.model.entity.Book;
import com.example.projectliboo.model.view.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findBookByTitle(String title);

    void addBook(BookCreateDto bookCreateDto);

    BookView getBookById(Long id);

    List<BookView> getAllBooks(String keyword);

    BookView map(Book book);

    void deleteBook(Long id);

    void editBook(Long id, BookEditDto bookEditDto);

}
