package com.example.projectliboo.controllers;

import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.view.BookView;
import com.example.projectliboo.service.Author.AuthorService;
import com.example.projectliboo.service.Book.BookService;
import com.example.projectliboo.service.Genre.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookController(BookService bookService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @ModelAttribute
    public BookView bookView(){
        return new BookView();
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable ("id") Long id, Model model){

        BookView bookView = bookService.getBookById(id);
        Genre genre=bookView.getGenre();

        model.addAttribute("book",bookView);
        model.addAttribute("genre",genre);

        return "book-view";
    }

    @GetMapping("/genre/{id}")
    public String viewBooksByGenre(@PathVariable("id") Long id, Model model){

        model.addAttribute("books",genreService.findGenreById(id).getBooks());
        model.addAttribute("genre",genreService.findGenreById(id));

        return "genre-view";
    }

    @GetMapping("/author/{id}")
    public String viewAuthor(@PathVariable("id") Long id, Model model ){

        model.addAttribute("author", authorService.findAuthorById(id));

        return "author-view";
    }



}
