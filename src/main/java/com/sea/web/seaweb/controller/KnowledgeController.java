package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.Knowledge;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user/knowledge")
public class KnowledgeController {

    private final UserService userService;

    @Autowired
    public KnowledgeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String showAll(Model model) {
        model.addAttribute(new Knowledge());
        return "knowledge";
    }

    @PostMapping("/update")
    public String update(Principal principal, @ModelAttribute Knowledge knowledge) {
        User u = userService.findByEmail(principal.getName());
        u.getKnowledgeList().add(knowledge);
        userService.save(u);
        return "knowledge";
    }

}
