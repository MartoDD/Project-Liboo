package com.example.projectliboo.validation.annotations;

import com.example.projectliboo.validation.UniqueBookNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueBookNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBookName {

    String message() default "Book already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
