package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        opt.ifPresent(model::addAttribute);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user, Model model) {
        Optional<User> opt = userService.findById(user.getId());
        if(opt.isPresent()) {
            User u = opt.get();
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPersonalAddress(user.getPersonalAddress());
            userService.save(u);
        }

        model.addAttribute("saved", true);
        return "profile";
    }

    @GetMapping
    public String findById(@RequestParam int id, Model model) {
        Optional<User> opt = userService.findById(id);
        opt.ifPresent(model::addAttribute);
        System.out.println("user " + id);
        return "user";
    }
}
