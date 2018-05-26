package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user/rest")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{knowledge}/{place}")
    public Iterable<User> findByKnowledgeAndPlace(@PathVariable String knowledge, @PathVariable String place, Principal principal) {
        User u = userService.findByEmail(principal.getName());
        return userService.findByKnowledgeAndPlace(knowledge, place, u.getId());
    }
}
