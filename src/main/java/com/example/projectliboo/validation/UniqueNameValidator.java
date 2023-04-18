package com.example.projectliboo.validation;

import com.example.projectliboo.service.Author.AuthorService;
import com.example.projectliboo.validation.annotations.UniqueName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private final AuthorService authorService;

    public UniqueNameValidator(AuthorService authorService) {
        this.authorService = authorService;
    }


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.authorService.findAuthorByName(s).isEmpty();
    }

}
