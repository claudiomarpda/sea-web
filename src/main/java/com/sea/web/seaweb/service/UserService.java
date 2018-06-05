package com.sea.web.seaweb.service;

import com.sea.web.seaweb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User u);

    void deleteById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer id);

    Iterable<User> findByUsualPlaces(String place);

    Iterable<User> findByKnowledge(String place);

    List<User> findByKnowledgeAndPlace(String place, String title, int userId);

    List<User> findByKnowledgeAndCity(String title, String city, int userId);

}
