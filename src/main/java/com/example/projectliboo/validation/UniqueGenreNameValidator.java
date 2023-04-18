package com.example.projectliboo.validation;

import com.example.projectliboo.service.Genre.GenreService;
import com.example.projectliboo.validation.annotations.UniqueGenreName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueGenreNameValidator implements ConstraintValidator<UniqueGenreName,String> {

    private final GenreService genreService;

    public UniqueGenreNameValidator(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.genreService.findGenreByName(s).isEmpty();
    }

}
