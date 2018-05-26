package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user/profile")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute(user);
        return "profile";
    }

    @PostMapping
    public String updateProfile(@ModelAttribute User user, Model model) {
        User u = userService.findById(user.getId());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPersonalAddress(user.getPersonalAddress());
        userService.save(u);

        model.addAttribute("saved", true);
        return "profile";
    }
}
