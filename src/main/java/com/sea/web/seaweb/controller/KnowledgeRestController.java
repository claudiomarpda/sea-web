package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.Knowledge;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.KnowledgeService;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/knowledge/rest")
public class KnowledgeRestController {

    private final UserService userService;
    private final KnowledgeService knowledgeService;

    @Autowired
    public KnowledgeRestController(UserService userService, KnowledgeService knowledgeService) {
        this.userService = userService;
        this.knowledgeService = knowledgeService;
    }

    @GetMapping("/all")
    public List<Knowledge> findAll(Principal principal) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        return opt.map(User::getKnowledgeList).orElse(null);
    }

    @PostMapping("/{title}/{description}")
    public void save(Principal principal, @PathVariable String title, @PathVariable String description) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if(opt.isPresent()) {
            opt.get().getKnowledgeList().add(new Knowledge(title, description));
            userService.save(opt.get());
        }
    }

    @DeleteMapping("/{index}")
    public void delete(Principal principal, @PathVariable int index) {
        Optional<User> opt = userService.findByEmail(principal.getName());
        if(opt.isPresent()) {
            User u = opt.get();
            Knowledge k = u.getKnowledgeList().get(index);
            u.getKnowledgeList().remove(index);
            userService.save(u);
            knowledgeService.deleteById(k.getId());
        }
    }
}
