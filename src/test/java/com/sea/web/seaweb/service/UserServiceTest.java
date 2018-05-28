package com.sea.web.seaweb.service;

import com.sea.web.seaweb.model.Address;
import com.sea.web.seaweb.model.Knowledge;
import com.sea.web.seaweb.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void crudOnUserShouldSucceed() {
        // Create a new User
        User u = new User("some@email.com", "First", "Last");
        u.setPersonalAddress(new Address("Country", "State", "City", "neighborhood"));
        ArrayList<Knowledge> kList = new ArrayList<>();
        kList.add(new Knowledge("Knowledge Title 1",
                "Knowledge description will usually be longer than the tittle"));
        kList.add(new Knowledge("Knowledge Title 2",
                "Knowledge description will usually be longer than the tittle"));
        u.setKnowledgeList(kList);
        ArrayList<String> upList = new ArrayList<>();
        upList.add("Place 1");
        upList.add("Place 2");
        u.setUsualPlaces(upList);
        u.setPassword("pass");

        // Save
        userService.save(u);
        // Update
        u.setLastName("Updated Last Name");
        userService.save(u);
        // Find
        Optional<User> opt2 = userService.findByEmail("some@email.com");
        assertNotNull(opt2.get());
        // Check data
        assertEquals(opt2.get().getLastName(), u.getLastName());

        Optional<User> opt3 = userService.findById(u.getId());
        assertNotNull(opt3.get());
        // Invalid id
        opt3 = userService.findById(9999999);
        assertFalse(opt3.isPresent());

        // Delete
        userService.deleteById(u.getId());
        // Find null
        Optional<User> opt = userService.findByEmail("some@email.com");
        assertFalse(opt.isPresent());
    }

    @Test
    public void findByPlaceShouldSucceed() {
        Iterable<User> it = userService.findByUsualPlaces("ufpb");
        assertNotNull(it);
        System.out.println("################# ");
        it.forEach(c -> System.out.println(c.getFirstName()));

        Iterable<User> it2 = userService.findByKnowledge("some");
        assertNotNull(it2);
        System.out.println("################# ");
        it2.forEach(c -> System.out.println(c.getFirstName()));

        Iterable<User> it3 = userService.findByKnowledgeAndPlace("some", "ifpb", 1);
        assertNotNull(it3);
        System.out.println("################# ");
        it3.forEach(c -> System.out.println(c.getFirstName()));
    }
}
