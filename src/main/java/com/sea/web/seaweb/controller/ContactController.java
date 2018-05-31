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
        if (optSender.isPresent()) {
            User sender = optSender.get();

            if (sender.getContactsRequest().containsKey(userId)) {
                return "redirect:/user/" + userId;
            }

            Optional<User> optReceiver = userService.findById(userId);
            if (optReceiver.isPresent()) {
                User receiver = optReceiver.get();
                ContactRequest request = new ContactRequest();
                request.setSenderId(sender.getId());
                request.setReceiverId(receiver.getId());
                sender.getContactsRequest().put(receiver.getId(), request);
                receiver.getContactsRequest().put(sender.getId(), request);
                userService.save(receiver);
                userService.save(sender);
            }
        }
        return "redirect:/user/" + userId;
    }

    @GetMapping("/all")
    public String showContacts(Principal principal, Model model) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if (opt.isPresent()) {
            User user = opt.get();
            List<User> userList = new ArrayList<>();

            user.getContactsRequest().forEach((k, v) -> {
                if (v.isAccepted()) {
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
        if (opt.isPresent()) {
            User user = opt.get();
            List<User> userList = new ArrayList<>();

            user.getContactsRequest().forEach((k, v) -> {
                if (!v.isAccepted() && !v.getSenderId().equals(user.getId())
                        && v.getStatus() == ContactRequest.Status.PENDING) {
                    Optional<User> o = userService.findById(k);
                    o.ifPresent(userList::add);
                }
            });
            model.addAttribute("userList", userList);
        }
        return "requests";
    }

    @PostMapping("/accept/{senderId}")
    public String accept(@PathVariable int senderId, Principal principal) {
        Optional<User> optReceiver = userService.findByEmail(principal.getName());
        Optional<User> optSender = userService.findById(senderId);

        User receiver = optReceiver.orElse(null);
        User sender = optSender.orElse(null);
        assert receiver != null;
        assert sender != null;
        setRequestStatus(senderId, sender, receiver, true);
        return "redirect:/user/contact/requests";
    }

    @PostMapping("/reject/{senderId}")
    public String reject(@PathVariable int senderId, Principal principal) {
        Optional<User> optReceiver = userService.findByEmail(principal.getName());
        Optional<User> optSender = userService.findById(senderId);

        User receiver = optReceiver.orElse(null);
        User sender = optSender.orElse(null);
        assert receiver != null;
        assert sender != null;
        setRequestStatus(senderId, sender, receiver, false);
        return "redirect:/user/contact/requests";
    }

    private void setRequestStatus(int senderId, User sender, User receiver, boolean accept) {
        // Receiver
        int receiverId;
        receiver.getContactsRequest().get(senderId).setAccepted(accept);
        userService.save(receiver);
        receiverId = receiver.getId();

        // Sender
        sender.getContactsRequest().get(receiverId).setAccepted(accept);
        userService.save(sender);
    }
}
