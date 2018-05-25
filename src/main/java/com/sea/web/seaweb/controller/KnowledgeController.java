package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.Knowledge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/knowledge")
public class KnowledgeController {

    @GetMapping
    public String knowledge(Model model) {
        model.addAttribute(new Knowledge());
        return "addKnowledge";
    }

}
