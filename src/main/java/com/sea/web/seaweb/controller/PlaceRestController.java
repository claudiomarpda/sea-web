package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user/place/rest")
public class PlaceRestController {

    private final UserService userService;

    @Autowired
    public PlaceRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<String> findAll(Principal principal) {
        User u = userService.findByEmail(principal.getName());
        return u.getUsualPlaces();
    }

    @PostMapping("/{title}")
    public void save(Principal principal, @PathVariable String title) {
        User u = userService.findByEmail(principal.getName());
        u.getUsualPlaces().add(title);
        userService.save(u);
    }

    @DeleteMapping("/{index}")
    public void delete(Principal principal, @PathVariable int index) {
        User u = userService.findByEmail(principal.getName());
        u.getUsualPlaces().remove(index);
        userService.save(u);
    }
}
