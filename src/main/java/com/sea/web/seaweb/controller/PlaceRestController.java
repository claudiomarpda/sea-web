package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
        Optional<User> opt = userService.findByEmail(principal.getName());
        return opt.map(User::getUsualPlaces).orElse(null);
    }

    @PostMapping("/{title}")
    public void save(Principal principal, @PathVariable String title) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        opt.ifPresent(c -> c.getUsualPlaces().add(title));
        opt.ifPresent(userService::save);
    }

    @DeleteMapping("/{index}")
    public void delete(Principal principal, @PathVariable int index) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        opt.ifPresent(c -> c.getUsualPlaces().remove(index));
        opt.ifPresent(userService::save);
    }
}
