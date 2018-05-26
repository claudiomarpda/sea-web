package com.sea.web.seaweb.repository;

import com.sea.web.seaweb.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    Iterable<User> findByUsualPlaces(String usualPlace);

    Iterable<User> findByKnowledgeListTitleContaining(String title);

    List<User> findByKnowledgeListTitleContainingAndUsualPlaces(String title, String usualPlace);
}
