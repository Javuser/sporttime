package com.nurbakyt.sporttime.controller;

import com.nurbakyt.sporttime.dto.MemberDto;
import com.nurbakyt.sporttime.entity.User;
import com.nurbakyt.sporttime.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new-user")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/new-user")
    public String registerUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String getUsers(Model model, String username){
        List<User> users = new ArrayList<>(userService.findAll());
        model.addAttribute("user", users);
        return "users/list";
    }

}
