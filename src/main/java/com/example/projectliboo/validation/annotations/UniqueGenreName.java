package com.example.projectliboo.validation.annotations;

import com.example.projectliboo.validation.UniqueGenreNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueGenreNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueGenreName {

    String message() default "Genre already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
