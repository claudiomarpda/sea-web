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

        // Save
        userService.save(u);
        // Update
        u.setLastName("Updated Last Name");
        userService.save(u);
        // Find
        User u2 = userService.findByEmail("some@email.com");
        assertNotNull(u2);
        // Check data
        assertEquals(u2.getLastName(), u.getLastName());

        User u3 = userService.findById(u.getId());
        assertNotNull(u3);
        // Invalid id
        u3 = userService.findById(9999999);
        assertNull(u3);

        // Delete
        userService.deleteById(u.getId());
        // Find null
        u = userService.findByEmail("some@email.com");
        assertNull(u);
    }
}
