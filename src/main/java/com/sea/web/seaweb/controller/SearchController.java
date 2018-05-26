package com.sea.web.seaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/search")
public class SearchController {

    @GetMapping
    public String search() {
        return "search";
    }
}
