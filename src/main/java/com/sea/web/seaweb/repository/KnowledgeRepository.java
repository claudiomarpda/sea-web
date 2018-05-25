package com.sea.web.seaweb.repository;

import com.sea.web.seaweb.model.Knowledge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeRepository extends CrudRepository<Knowledge, Integer> {
}
