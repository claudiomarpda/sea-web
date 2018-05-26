package com.sea.web.seaweb.service;

import com.sea.web.seaweb.model.User;

import java.util.List;

public interface UserService {

    void save(User u);

    void deleteById(Integer id);

    User findByEmail(String email);

    User findById(Integer id);

    Iterable<User> findByUsualPlaces(String place);

    Iterable<User> findByKnowledge(String place);

    List<User> findByKnowledgeAndPlace(String place, String title, int userId);
}
