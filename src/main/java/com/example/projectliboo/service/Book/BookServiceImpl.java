package com.example.projectliboo.service.Book;

import com.example.projectliboo.model.dtos.BookCreateDto;
import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.model.entity.Book;
import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.enums.GenreEnum;
import com.example.projectliboo.model.view.BookView;
import com.example.projectliboo.repository.AuthorRepository;
import com.example.projectliboo.repository.BookRepository;
import com.example.projectliboo.repository.GenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Book> findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public void addBook(BookCreateDto bookCreateDto) {

        Book book = new Book();
        Author author = authorRepository.findAuthorByName(bookCreateDto.getAuthorName()).orElse(null);
        Genre genre = genreRepository.findGenreByGenreName(bookCreateDto.getGenre()).orElse(null);
        book.setDescription(bookCreateDto.getDescription());
        book.setTitle(bookCreateDto.getTitle());
        book.setPages(bookCreateDto.getNumberOfPages());
        book.setImageUrl(bookCreateDto.getImageUrl());
        book.setAuthor(author);
        book.getGenres().add(genre);
        bookRepository.save(book);
        author.getBooks().add(book);
        authorRepository.save(author);
        genre.getBooks().add(book);
        genreRepository.save(genre);

    }

    @Override
    public BookView getBookById(Long id) {
       Book book =bookRepository.findById(id).orElse(null);
       BookView bookView = map(book);

        return bookView;
    }

    @Override
    public Page<BookView> getAllBooks(Pageable pageable) {

        return bookRepository.findAll(pageable).map(this::map);
    }

    @Override
    public BookView map(Book book) {
        BookView bookView = new BookView();
        bookView.setTitle(book.getTitle());
        bookView.setDescription(book.getDescription());
        bookView.setPages(book.getPages());
        bookView.setGenres(book.getGenres());
        bookView.setAuthor(book.getAuthor());
        bookView.setImageUrl(book.getImageUrl());
        bookView.setId(book.getId());
        return bookView;
    }


}
