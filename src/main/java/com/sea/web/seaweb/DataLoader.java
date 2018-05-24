package com.sea.web.seaweb;

import com.sea.web.seaweb.model.Knowledge;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("w@w.com", "First", "Last");
        List<Knowledge> kList = new ArrayList<>();
        kList.add(new Knowledge("Some title", "Some description"));
        user.setKnowledgeList(kList);

        userService.save(user);
    }
}
