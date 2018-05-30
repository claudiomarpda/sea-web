package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import com.sea.web.seaweb.util.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if (opt.isPresent()) {
            User user = opt.get();
            model.addAttribute(user);

            boolean hasImage = false;
            if (user.getImage() != null) {
                hasImage = true;
            }
            model.addAttribute("hasImage", hasImage);
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User user, MultipartFile file, Model model) {
        Optional<User> opt = userService.findById(user.getId());
        if (opt.isPresent()) {
            User existent = opt.get();
            UserFactory.update(existent, user);

            if (file != null && !file.isEmpty()) {
                try {
                    existent.setImage(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (existent.getImage() != null) {
                model.addAttribute("hasImage", true);
            }
            userService.save(existent);
        }
        model.addAttribute("saved", true);
        return "profile";
    }

    @GetMapping
    public String findById(@RequestParam int id, Model model) {
        Optional<User> opt = userService.findById(id);
        opt.ifPresent(c -> model.addAttribute("user", c));
        return "user";
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<InputStreamResource> showRawImage(@PathVariable int userId) {
        Optional<User> opt = userService.findById(userId);
        byte[] file = new byte[0];
        if (opt.isPresent()) {
            file = opt.get().getImage();
        }
        return ResponseEntity
                .ok()
                .contentLength(file.length)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(new ByteArrayInputStream(file)));
    }
}
