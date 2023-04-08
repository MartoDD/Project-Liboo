package com.example.projectliboo.validation;


import com.example.projectliboo.service.User.UserService;
import com.example.projectliboo.validation.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.userService.findUserByEmail(s).isEmpty();
    }
}
