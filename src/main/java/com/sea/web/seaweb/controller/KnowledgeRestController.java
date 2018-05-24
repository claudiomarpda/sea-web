package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.Knowledge;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/knowledge/rest")
public class KnowledgeRestController {

    private final UserService userService;

    @Autowired
    public KnowledgeRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<Knowledge> findAll(Principal principal) {
        User u = userService.findByEmail(principal.getName());
        return u.getKnowledgeList();
    }
}
