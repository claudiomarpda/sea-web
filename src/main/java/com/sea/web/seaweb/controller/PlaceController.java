package com.sea.web.seaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/place")
public class PlaceController {

    @GetMapping
    public String place() {
        return "addPlace";
    }
}
