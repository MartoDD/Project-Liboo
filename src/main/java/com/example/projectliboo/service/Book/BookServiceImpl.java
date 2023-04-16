package com.example.projectliboo.service.Book;

import com.example.projectliboo.model.dtos.BookCreateDto;
import com.example.projectliboo.model.dtos.BookEditDto;
import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.model.entity.Book;
import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.view.BookView;
import com.example.projectliboo.repository.AuthorRepository;
import com.example.projectliboo.repository.BookRepository;
import com.example.projectliboo.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Book book = bookRepository.findById(id).orElse(null);
        BookView bookView = map(book);

        return bookView;
    }

    @Override
    public List<BookView> getAllBooks(String keyword) {

        if (keyword == null) {

            return bookRepository.findAll().stream().map(this::map).collect(Collectors.toList());
        }

        return bookRepository.findBooksByTitleContaining(keyword).stream().map(this::map).collect(Collectors.toList());
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

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void editBook(Long id, BookEditDto bookEditDto) {

        Book book = bookRepository.findById(id).orElse(null);
        book.setTitle(bookEditDto.getBookTitle());
        book.setImageUrl(bookEditDto.getBookCover());
        book.setTitle(bookEditDto.getBookTitle());
        book.setPages(bookEditDto.getBookPages());
        bookRepository.save(book);

    }


}
