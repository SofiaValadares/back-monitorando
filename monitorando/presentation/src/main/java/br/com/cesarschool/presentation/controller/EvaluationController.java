package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.domain.entity.EvaluationEntity;
import br.com.cesarschool.domain.service.EvaluationService;
import br.com.cesarschool.presentation.dto.evaluation.EvaluationRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // POST /evaluations
    @PostMapping
    public ResponseEntity<String> evaluateStudent(@RequestBody EvaluationRequest request) {
        evaluationService.evaluate(
            request.getStudentId(),
            request.getMonitorId(),
            request.getAttendanceDate(),
            request.getRating(),
            request.getComment()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("Avaliação registrada com sucesso.");
    }

    // GET /evaluations/student/{studentId}
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EvaluationEntity>> getEvaluationsByStudent(@PathVariable Long studentId) {
        List<EvaluationEntity> evaluations = evaluationService.getEvaluationsByStudent(studentId);
        return ResponseEntity.ok(evaluations);
    }

}
