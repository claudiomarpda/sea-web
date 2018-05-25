package com.sea.web.seaweb.service.impl;

import com.sea.web.seaweb.repository.KnowledgeRepository;
import com.sea.web.seaweb.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {


    private final KnowledgeRepository knowledgeRepository;

    @Autowired
    public KnowledgeServiceImpl(KnowledgeRepository knowledgeRepository) {
        this.knowledgeRepository = knowledgeRepository;
    }

    @Override
    public void deleteById(int id) {
        knowledgeRepository.deleteById(id);
    }
}
