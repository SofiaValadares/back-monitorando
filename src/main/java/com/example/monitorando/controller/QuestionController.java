package com.example.monitorando.controller;

import com.example.monitorando.entity.QuestionEntity;
import com.example.monitorando.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionEntity> create(@RequestBody QuestionEntity question) {
        return ResponseEntity.ok(questionService.save(question));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionEntity> getById(@PathVariable Long id) {
        return questionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<QuestionEntity>> getAll() {
        return ResponseEntity.ok(questionService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
