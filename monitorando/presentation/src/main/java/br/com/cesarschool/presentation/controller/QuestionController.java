package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.application.service.QuestionService;
import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.presentation.dto.question.QuestionChatRequestDTO;
import br.com.cesarschool.presentation.dto.question.QuestionRequestDTO;
import br.com.cesarschool.presentation.dto.question.QuestionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

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

    @PostMapping("/chat/answer")
    public ResponseEntity<String> answerQuestionInChat(@RequestBody QuestionChatRequestDTO request) {

        questionService.sendAnswerToQuestion(
                request.getQuestionId(),
                request.getUserId(),
                request.getAnswer()
        );

        return ResponseEntity.ok("Question to monitor created successfully.");
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<QuestionResponseDTO>> getStudentQuestions(@PathVariable Long id) {
        List<QuestionEntity> questionEntities = questionService.getAllStudentsQuestions(id);

        List<QuestionResponseDTO> response = questionEntities.stream()
                .map(q -> new QuestionResponseDTO(q.getId(), q.getQuestion(), q.getStudentId(), q.getDisciplineId(), q.getPublic(), q.getStatus().getDisplayName(), q.getMonitorId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
