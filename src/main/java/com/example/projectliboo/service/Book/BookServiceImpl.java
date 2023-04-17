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
import com.example.projectliboo.repository.UserRepository;
import com.example.projectliboo.service.User.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, UserRepository userRepository, UserService userService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.userService = userService;
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
        book.setGenre(genre);
        bookRepository.save(book);
        author.getBooks().add(book);
        authorRepository.save(author);
        genre.getBooks().add(book);
        genreRepository.save(genre);

    }

    @Override
    public BookView getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        return map(book);
    }

    @Override
    public BookEditDto getBookByIdForEdit(Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        return mapBookEdit(book);
    }

    @Override
    public BookEditDto mapBookEdit(Book book) {

        BookEditDto bookEditDto = new BookEditDto();
        bookEditDto.setTitle(book.getTitle());
        bookEditDto.setBookCover(book.getImageUrl());
        bookEditDto.setBookDescription(book.getDescription());
        bookEditDto.setId(book.getId());

        return bookEditDto;
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
        bookView.setGenre(book.getGenre());
        bookView.setAuthor(book.getAuthor());
        bookView.setImageUrl(book.getImageUrl());
        bookView.setId(book.getId());
        return bookView;
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        Author author = authorRepository.findById(book.getAuthor().getId()).orElse(null);
        author.getBooks().removeIf(b->b.getId().equals(id));
        authorRepository.save(author);
        Genre genre = genreRepository.findById(book.getGenre().getId()).orElse(null);
        genre.getBooks().removeIf(b->b.getId().equals(id));
        genreRepository.save(genre);
        userRepository.findAll().forEach(u->userService.removeBook(id,u.getUsername()));
        bookRepository.deleteById(id);
    }

    @Override
    public void editBook(Long id, BookEditDto bookEditDto) {

        Book book = bookRepository.findById(id).orElse(null);
        book.setTitle(bookEditDto.getTitle());
        book.setImageUrl(bookEditDto.getBookCover());
        book.setPages(bookEditDto.getBookPages());
        bookRepository.save(book);

    }


}
