package com.sea.web.seaweb.service;

import com.sea.web.seaweb.model.User;

public interface UserService {

    void save(User u);

    void deleteById(Integer id);

    User findByEmail(String email);

    User findById(Integer id);
}
