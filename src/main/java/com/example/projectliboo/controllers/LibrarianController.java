package com.example.projectliboo.controllers;

import com.example.projectliboo.model.dtos.*;
import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.view.AuthorListView;
import com.example.projectliboo.model.view.AuthorView;
import com.example.projectliboo.model.view.BookView;
import com.example.projectliboo.model.view.GenreListView;
import com.example.projectliboo.service.Author.AuthorService;
import com.example.projectliboo.service.Book.BookService;
import com.example.projectliboo.service.Genre.GenreService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final GenreService genreService;

    public LibrarianController(AuthorService authorService, BookService bookService, GenreService genreService) {

        this.authorService = authorService;
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @ModelAttribute
    public BookCreateDto bookCreateDto() {
        return new BookCreateDto();
    }

    @ModelAttribute
    public BookEditDto bookEditDto() {
        return new BookEditDto();
    }

    @ModelAttribute
    public AuthorEditDto authorEditDto() {
        return new AuthorEditDto();
    }

    @ModelAttribute
    public GenreListView allGenres(Model model) {
        GenreListView genreListView = new GenreListView();
        genreListView.setGenres(genreService.getAllGenres());
        model.addAttribute("genre", genreService.getAllGenres());
        return genreListView;
    }

    @ModelAttribute
    public AuthorListView allAuthors(Model model) {
        AuthorListView authorListView = new AuthorListView();
        authorListView.setAllAuthors(authorService.getAllAuthors());
        model.addAttribute("author", authorService.getAllAuthors());
        return authorListView;
    }

    @ModelAttribute
    public GenreCreateDto genreCreateDto() {
        return new GenreCreateDto();
    }

    @ModelAttribute
    public AuthorCreateDto authorCreateDto() {
        return new AuthorCreateDto();
    }


    @GetMapping("/add-book")
    public String add() {
        return "add-book";
    }

    @GetMapping("/add-author")
    public String addAuthor() {
        return "add-author";
    }

    @GetMapping("/add-genre")
    public String addGenre() {
        return "add-genre";
    }

    @GetMapping("/home")
    public String home() {

        return "librarian-page";

    }

    @GetMapping("/edit-author/{id}")
    public String editAuthor(@PathVariable("id") Long id, Model model) {

        model.addAttribute("authory", authorService.findAuthorById(id));
        model.addAttribute("authorEditDto", authorService.getAuthorByIdForEdit(id));

        return "edit-author";
    }

    @GetMapping("/edit-book/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {

        model.addAttribute("booksy", bookService.getBookByIdForEdit(id));
        model.addAttribute("bookEditDto", bookService.getBookByIdForEdit(id));

        return "edit-book";
    }

    @PostMapping("/edit-book/{id}")
    public String editBook(@PathVariable("id") Long id, @Valid BookEditDto bookEditDto, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("bookEditDto", bookEditDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookEditDto", bindingResult);

            return "redirect:/librarian/edit-book/" + id;
        }

        bookService.editBook(id, bookEditDto);

        return "redirect:/librarian/manage-books";
    }

    @PostMapping("/edit-author/{id}")
    public String editAuthorSubmit(@PathVariable("id") Long id, @Valid AuthorEditDto authorEditDto, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("authorEditDto", authorEditDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authorEditDto", bindingResult);

            return "redirect:/librarian/edit-author/" + id;
        }

        authorService.editAuthor(id, authorEditDto);

        return "redirect:/librarian/manage-authors";
    }

    @GetMapping("/manage-books")
    public String getAllBooks(Model model, @Param("keyword") String keyword) {

        List<BookView> books = bookService.getAllBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);


        return "manage-books";

    }
    @GetMapping("/manage-genres")
    public String getAllGenres(Model model, @Param("keyword") String keyword){

        List<Genre> genres=genreService.getAllGenresByKeyword(keyword);
        model.addAttribute("genres", genres);
        model.addAttribute("keyword", keyword);

        return "manage-genres";
    }

    @GetMapping("/manage-authors")
    public String getAllAuthors(Model model, @Param("keyword") String keyword) {


        List<AuthorView> authors = authorService.getAllAuthors(keyword);
        model.addAttribute("authors", authors);
        model.addAttribute("keyword", keyword);


        return "manage-authors";

    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {

        bookService.deleteBook(id);

        return "redirect:/librarian/home";
    }

    @PostMapping("/add-author")
    public String addAuthorInformation(@Valid AuthorCreateDto authorCreateDto,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("authorCreateDto", authorCreateDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.authorCreateDto", bindingResult);

            return "redirect:/librarian/add-author";


        }
        authorService.addAuthor(authorCreateDto);

        return "redirect:/librarian/home";
    }

    @PostMapping("/add-book")
    public String addBook(@Valid BookCreateDto bookCreateDto,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("bookCreateDto", bookCreateDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.bookCreateDto", bindingResult);

            return "redirect:/librarian/add-book";
        }

        bookService.addBook(bookCreateDto);

        return "redirect:/librarian/home";
    }

    @PostMapping("/add-genre")
    public String addNewGenre(@Valid GenreCreateDto genreCreateDto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("genreCreateDto", genreCreateDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.genreCreateDto", bindingResult);

            return "redirect:/librarian/add-genre";
        }
        genreService.addGenre(genreCreateDto);

        return "redirect:/librarian/home";

    }


}
