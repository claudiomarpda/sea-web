package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.ContactRequest;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    private final UserService userService;

    @Autowired
    public ContactController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/request/{userId}")
    public String requestContact(Principal principal, @PathVariable int userId) {
        Optional<User> optSender = userService.findByEmail(principal.getName());
        if(optSender.isPresent()) {
            User sender = optSender.get();

            if(sender.getContactsRequest().containsKey(userId)) {
                return "redirect:/user/" + userId;
            }

            ContactRequest request = new ContactRequest();
            sender.getContactsRequest().put(userId, request);

            userService.save(sender);
            Optional<User> optReceiver = userService.findById(userId);
            if(optReceiver.isPresent()) {
                User receiver = optReceiver.get();
                receiver.getContactsRequest().put(sender.getId(), request);
                userService.save(receiver);
            }
        }
        return "redirect:/user/" + userId;
    }
}
