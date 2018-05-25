package com.sea.web.seaweb.controller;

import com.sea.web.seaweb.model.Knowledge;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.KnowledgeService;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
        User u = userService.findByEmail(principal.getName());
        return u.getKnowledgeList();
    }

    @PostMapping("/{title}/{description}")
    public void save(Principal principal, @PathVariable String title, @PathVariable String description) {
        User u = userService.findByEmail(principal.getName());
        u.getKnowledgeList().add(new Knowledge(title, description));
        userService.save(u);
    }

    @DeleteMapping("/{index}")
    public void delete(Principal principal, @PathVariable String index) {
        User u = userService.findByEmail(principal.getName());
        int i = Integer.valueOf(index);

        Knowledge k = u.getKnowledgeList().get(i);
        u.getKnowledgeList().remove(i);
        userService.save(u);

        knowledgeService.deleteById(k.getId());

    }
}
