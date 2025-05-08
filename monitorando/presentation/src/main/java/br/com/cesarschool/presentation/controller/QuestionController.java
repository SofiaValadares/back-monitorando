package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.domain.service.QuestionService;
import br.com.cesarschool.presentation.dto.question.QuestionRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/to-monitor")
    public ResponseEntity<String> makeQuestionToMonitor(@RequestBody QuestionRequestDTO request) {
        questionService.makeQuestionToMonitor(
                request.getStudentId(),
                request.getQuestion(),
                request.getDisciplineId(),
                request.getMonitorId()
        );
        return ResponseEntity.ok("Question to monitor created successfully.");
    }
}
