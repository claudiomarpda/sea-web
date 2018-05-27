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

@Controller
@RequestMapping("/user/search")
public class SearchController {

    private final UserService userService;

    @Autowired
    public SearchController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String search(Model model) {
        Search search = new Search();
        model.addAttribute(search);
        return "search";
    }

    @GetMapping("/")
    public String findByKnowledgeAndPlace(@ModelAttribute Search search, Principal principal) {
        User u = userService.findByEmail(principal.getName());
        List<User> users = userService.findByKnowledgeAndPlace(search.getKnowledge(), search.getPlace(), u.getId());
        search.setResult(users);
        return "search";
    }
}
