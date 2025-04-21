package com.example.monitorando.service;

import com.example.monitorando.entity.QuestionEntity;
import com.example.monitorando.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionEntity save(QuestionEntity question) {
        return questionRepository.save(question);
    }

    public Optional<QuestionEntity> getById(Long id) {
        return questionRepository.findById(id);
    }

    public List<QuestionEntity> getAll() {
        return questionRepository.findAll();
    }

    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
