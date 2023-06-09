package com.example.projectliboo.validation.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import com.example.projectliboo.validation.UniqueEmailValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "Email already exist!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
