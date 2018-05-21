package com.sea.web.seaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("homeMessage", "Hello from server");
        return "home";
    }

    // TODO: Remove this mapping from here and take it to its on controller.
    @RequestMapping("/user/profile")
    public String profile() {
        return "/user/profile";
    }
}
