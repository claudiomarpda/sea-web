package com.sea.web.seaweb;

import com.sea.web.seaweb.model.Knowledge;
import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private PasswordEncoder encoder;

    @Autowired
    public DataLoader(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("w@w.com", "First", "Last");
        List<Knowledge> kList = new ArrayList<>();
        kList.add(new Knowledge("Some title", "Some description"));
        user.setKnowledgeList(kList);
        user.getUsualPlaces().add("UFPB");
        user.setPassword(encoder.encode("w"));
        user.setGender("Male");
        userService.save(user);

        for (int i = 1; i <= 5; i++) {
            user = new User("w@w.com" + i, "First" + i, "Last" + i);
            kList = new ArrayList<>();
            kList.add(new Knowledge("Some title" + i, "Some description" + i));
            user.setKnowledgeList(kList);
            user.getUsualPlaces().add("UFPB");
            user.setPassword(encoder.encode("pass" + i));
            user.setGender("Male");
            userService.save(user);
        }
        for (int i = 6; i <= 10; i++) {
            user = new User("w@w.com" + i, "First" + i, "Last" + i);
            kList = new ArrayList<>();
            kList.add(new Knowledge("Some title" + i, "Some description" + i));
            user.setKnowledgeList(kList);
            user.getUsualPlaces().add("IFPB");
            user.setPassword(encoder.encode("pass" + i));
            user.setGender("Female");
            userService.save(user);
        }
    }
}
