package com.example.projectliboo.validation.annotations;

import com.example.projectliboo.validation.UniqueNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueName {

    String message() default "Author already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
