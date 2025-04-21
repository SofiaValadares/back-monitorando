package com.example.monitorando.controller;

import com.example.monitorando.DTO.QuestionRequestDTO;
import com.example.monitorando.service.QuestionService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

	@Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionRequestDTO request) {
        String response = questionService.createQuestion(request);
        if (response.startsWith("Erro")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
