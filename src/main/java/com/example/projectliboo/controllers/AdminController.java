package com.example.projectliboo.controllers;

import com.example.projectliboo.model.dtos.UserRoleChangeDto;
import com.example.projectliboo.model.dtos.UserSearchDto;
import com.example.projectliboo.model.view.UserView;
import com.example.projectliboo.service.User.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private UserView userView;

    public AdminController(UserService userService, UserView userView) {
        this.userService = userService;
        this.userView = userView;
    }

    @ModelAttribute
    public UserRoleChangeDto userRoleChangeDto(){
        return new UserRoleChangeDto();
    }

    @ModelAttribute
    public UserSearchDto userSearchDto() {
        return new UserSearchDto();
    }

    @GetMapping("/home")
    public String home(Model model, @Param("keyword") String keyword) {

        List<UserView> users = userService.getAllUsers(keyword);
        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);

        return "admin-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);

        return "redirect:/admin/home" ;
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, UserRoleChangeDto userRoleChangeDto){

        userService.changeUserRole(userRoleChangeDto,id);

        return "redirect:/admin/home";
    }

}
