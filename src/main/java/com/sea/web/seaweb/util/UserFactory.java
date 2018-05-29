package com.sea.web.seaweb.util;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.model.UserForm;

public class UserFactory {

    public static User create(UserForm userForm) {
        User u = new User();
        u.setFirstName(userForm.getFirstName());
        u.setLastName(userForm.getLastName());
        u.setGender(userForm.getGender());
        u.setEmail(userForm.getEmail());
        u.setPassword(userForm.getPassword());
        return u;
    }

    public static void update(User user, User updater) {
        if(updater.getFirstName() != null)
            user.setFirstName(updater.getFirstName());
        if(updater.getLastName() != null)
            user.setLastName(updater.getLastName());
        if(updater.getGender() != null)
            user.setGender(updater.getGender());
        if(updater.getEmail() != null)
            user.setEmail(updater.getEmail());
        if(updater.getPersonalAddress()!= null)
            user.setPersonalAddress(updater.getPersonalAddress());
    }
}
