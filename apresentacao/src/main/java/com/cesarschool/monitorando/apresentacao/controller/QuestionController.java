package com.cesarschool.monitorando.apresentacao.controller;

import com.cesarschool.monitorando.apresentacao.DTO.QuestionRequestDTO;
import com.cesarschool.monitorando.apresentacao.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
