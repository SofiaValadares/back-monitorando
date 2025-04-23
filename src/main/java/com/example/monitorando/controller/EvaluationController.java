package com.example.monitorando.controller;

import com.example.monitorando.DTO.EvaluationRequestDTO;
import com.example.monitorando.service.EvaluationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes")
public class EvaluationController { 

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<?> avaliar(@RequestBody @Valid EvaluationRequestDTO dto) {
        try {
            evaluationService.avaliar(dto);
            return ResponseEntity.ok("Avaliação registrada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
