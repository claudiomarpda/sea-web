package com.sea.web.seaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AccountController {

    @RequestMapping("/signin")
    public String signIn(Principal principal) {
        // Check if user is already authenticated
        return principal != null ? "redirect:/user/profile" : "signIn";
    }

    @RequestMapping("/signin/error")
    public String signInError(Model model) {
        model.addAttribute("signInError", true);
        return "signIn";
    }
}
