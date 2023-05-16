package com.example.projectliboo.controllers;

import com.example.projectliboo.model.dtos.UserEditDto;
import com.example.projectliboo.model.entity.Book;
import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.view.BookView;
import com.example.projectliboo.service.Book.BookService;
import com.example.projectliboo.service.User.UserService;
import jakarta.validation.Valid;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final BookService bookService;
    private final UserService userService;

    public UserController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @ModelAttribute
    public UserEditDto userEditDto() {
        return new UserEditDto();
    }

    @GetMapping("/profile")
    public String displayProfileAndBooks(Principal principal, Model model) {

        User user = userService.findUserByUsername(principal.getName());
        List<Book> allBooks = user.getBooks();
        List<Book> currentlyReading = user.getCurrentlyReading();
        List<Book> wantToRead = user.getTbrBooks();
        List<Book> read = user.getReadBooks();
        model.addAttribute("currentUser", user);
        model.addAttribute("allUserBooks", allBooks);
        model.addAttribute("currentlyReading", currentlyReading);
        model.addAttribute("wantToRead", wantToRead);
        model.addAttribute("read", read);

        return "profile";
    }

    @GetMapping("/books")
    public String displayBooks(@Param("keyword") String keyword, Model model) {

        List<BookView> books = bookService.getAllBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);

        return "books";
    }

    @GetMapping("/want-to-read/{id}")
    public String wantToRead(@PathVariable("id") Long id, Principal principal) {

        userService.wantToRead(id, principal);

        return "redirect:/user/books";
    }

    @GetMapping("/currently-reading/{id}")
    public String currentlyReading(@PathVariable("id") Long id, Principal principal) {

        userService.currentlyReading(id, principal);

        return "redirect:/user/books";
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable("id") Long id, Principal principal) {

        userService.read(id, principal);

        return "redirect:/user/books";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Principal principal) {

        userService.remove(id, principal);

        return "redirect:/user/books";
    }

    @GetMapping("/edit")
    public String edit(Principal principal,Model model) {

        model.addAttribute("user",userService.findUserByUsername(principal.getName()));
        model.addAttribute("userEditDto",userService.getUserByUsernameForEdit(principal.getName()));
        return "edit-user";
    }

    @PostMapping("/edit")
    public String editUser(@Valid UserEditDto userEditDto, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userEditDto", userEditDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userEditDto", bindingResult);

            return "redirect:/user/edit";
        }

        userService.editUser(userEditDto, principal);

        return "redirect:/user/profile";
    }

}
