package com.example.projectliboo.service.Book;

import com.example.projectliboo.model.dtos.BookCreateDto;
import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.model.entity.Book;
import com.example.projectliboo.repository.AuthorRepository;
import com.example.projectliboo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public void addBook(BookCreateDto bookCreateDto) {

        Book book = new Book();
        Author author=authorRepository.findAuthorByName(bookCreateDto.getAuthorName()).orElse(null);
        book.setDescription(bookCreateDto.getDescription());
        book.setTitle(bookCreateDto.getTitle());
        book.setPages(bookCreateDto.getNumberOfPages());
        book.setImageUrl(bookCreateDto.getImageUrl());
        book.setAuthor(author);
        bookRepository.save(book);
        author.getBooks().add(book);
        authorRepository.save(author);

    }
}
