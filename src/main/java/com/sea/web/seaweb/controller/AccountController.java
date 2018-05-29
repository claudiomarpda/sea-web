package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.model.UserForm;
import com.sea.web.seaweb.service.UserService;
import com.sea.web.seaweb.util.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signIn(Principal principal) {
        // Check if user is already authenticated
        return principal != null ? "redirect:/user/search" : "signIn";
    }

    @GetMapping("/signin/error")
    public String signInError(Model model) {
        model.addAttribute("signInError", true);
        return "signIn";
    }

    @GetMapping("/signin/after-signup")
    public String signInAfterSignUp(Model model, @RequestParam String name) {
        model.addAttribute("afterSignedUp", true);
        model.addAttribute("userName", name);
        return "signIn";
    }

    @GetMapping("/signup")
    public String showSignUp(Model model) {
        model.addAttribute(new UserForm());
        return "signUp";
    }

    @PostMapping("/signup")
    public String checkSignUp(@Valid UserForm userForm, BindingResult bindingResult, Model model) {
        System.out.println(userForm);
        if(!bindingResult.hasErrors() && userForm != null) {
            Optional<User> opt = userService.findByEmail(userForm.getEmail());
            if(opt.isPresent()) {
                model.addAttribute("emailExists", true);
                return "signUp";
            }
            User newUser = UserFactory.create(userForm);
            userService.save(newUser);
            return "redirect:/signin/after-signup?name=" + userForm.getFirstName();
        }
        return "signUp";
    }
}
