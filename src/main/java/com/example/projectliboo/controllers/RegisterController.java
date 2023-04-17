package com.example.projectliboo.controllers;

import com.example.projectliboo.model.dtos.UserRegisterDto;
import com.example.projectliboo.service.User.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    private UserRegisterDto userRegisterDto() {
        return new UserRegisterDto();
    }

    @GetMapping("/register")
    private String register() {
        return "register";
    }



    @PostMapping("/register")
    private String registerUser(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (!userRegisterDto.getConfirmPassword().equals(userRegisterDto.getPassword())) {
            bindingResult.addError(new FieldError("differentPassword", "confirmPassword", "Password does not match!"));
        }

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegisterDto", userRegisterDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDto", bindingResult);

            return "redirect:/user/register";

        }

        userService.registerUser(userRegisterDto);

        return "redirect:/";
    }
}
