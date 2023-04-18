package com.example.projectliboo.validation;

import com.example.projectliboo.service.Author.AuthorService;
import com.example.projectliboo.service.Book.BookService;
import com.example.projectliboo.validation.annotations.UniqueBookName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueBookNameValidator implements ConstraintValidator<UniqueBookName,String> {

    private final BookService bookService;


    public UniqueBookNameValidator(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.bookService.findBookByTitle(s).isEmpty();
    }

}
