package com.sea.web.seaweb.service.impl;

import com.sea.web.seaweb.model.User;
import com.sea.web.seaweb.repository.UserRepository;
import com.sea.web.seaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User u) {
        userRepository.save(u);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> findByUsualPlaces(String place) {
        return userRepository.findByUsualPlaces(place);
    }

    @Override
    public Iterable<User> findByKnowledge(String title) {
        return userRepository.findByKnowledgeListTitleContaining(title);
    }

    @Override
    public List<User> findByKnowledgeAndPlace(String title, String place, int userId) {
        List<User> list = userRepository.findDistinctByKnowledgeListTitleContainingAndUsualPlaces(title, place);
        list.removeIf(c -> c.getId().equals(userId));
        return list;
    }

    @Override
    public List<User> findByKnowledgeAndCity(String title, String city, int userId) {
        List<User> list = userRepository.findDistinctByKnowledgeListTitleContainingAndPersonalAddressCityContaining(title, city);
        list.removeIf(c -> c.getId().equals(userId));
        return list;
    }
}