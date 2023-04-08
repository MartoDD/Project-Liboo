package com.example.projectliboo.controllers;

import com.example.projectliboo.model.dtos.UserLoginDto;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class LoginController {

    @ModelAttribute
    private UserLoginDto userLoginDto() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    private String login() {
        return "login";
    }

    @PostMapping("/login-error")
    private String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/user/login";

    }

    @GetMapping("/logout")
    private String logout() {
        return "redirect:/";
    }
}
