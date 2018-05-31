package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.ContactRequest;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
            request.setSenderId(sender.getId());
            sender.getContactsRequest().put(userId, request);

            userService.save(sender);
            Optional<User> optReceiver = userService.findById(userId);
            if(optReceiver.isPresent()) {
                User receiver = optReceiver.get();
                request.setReceiverId(receiver.getId());
                receiver.getContactsRequest().put(sender.getId(), request);
                userService.save(receiver);
            }
        }
        return "redirect:/user/" + userId;
    }

    @GetMapping("/all")
    public String showContacts(Principal principal, Model model) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if(opt.isPresent()) {
            User user = opt.get();
            List<User> userList = new ArrayList<>();

            user.getContactsRequest().forEach((k, v) -> {
                if(v.isAccepted()) {
                    Optional<User> o = userService.findById(k);
                    o.ifPresent(userList::add);
                }
            });
            model.addAttribute("userList", userList);
        }
        return "contacts";
    }

    @GetMapping("/requests")
    public String showRequests(Principal principal, Model model) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if(opt.isPresent()) {
            User user = opt.get();
            List<User> userList = new ArrayList<>();

            user.getContactsRequest().forEach((k, v) -> {
                if(!v.isAccepted() && !v.getSenderId().equals(user.getId())
                        && v.getStatus() == ContactRequest.Status.PENDING) {
                    Optional<User> o = userService.findById(k);
                    o.ifPresent(userList::add);
                }
            });
            model.addAttribute("userList", userList);
        }
        return "requests";
    }
}
