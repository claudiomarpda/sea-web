package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.Search;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/search")
public class SearchController {

    private final UserService userService;

    @Autowired
    public SearchController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/place")
    public String searchByPlace(Model model) {
        Search search = new Search();
        model.addAttribute(search);
        model.addAttribute("byPlace", true);
        return "search";
    }

    @GetMapping("/city")
    public String searchByCity(Model model) {
        Search search = new Search();
        model.addAttribute(search);
        model.addAttribute("byCity", true);
        return "search";
    }

    @GetMapping("/find-by-place")
    public String findByKnowledgeAndPlace(@ModelAttribute Search search, Principal principal, Model model) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if (opt.isPresent()) {
            List<User> users = userService.findByKnowledgeAndPlace(
                    search.getKnowledge(), search.getPlace(), opt.get().getId());
            search.setResult(users);
        }
        model.addAttribute("byPlace", true);
        return "search";
    }

    @GetMapping("/find-by-city")
    public String findByKnowledgeAndCity(@ModelAttribute Search search, Principal principal, Model model) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if (opt.isPresent()) {
            List<User> users = userService.findByKnowledgeAndCity(
                    search.getKnowledge(), search.getPlace(), opt.get().getId());
            search.setResult(users);
        }
        model.addAttribute("byCity", true);
        return "search";
    }
}
