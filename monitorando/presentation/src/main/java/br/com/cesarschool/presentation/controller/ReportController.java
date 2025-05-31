package br.com.cesarschool.presentation.controller;

import br.com.cesarschool.domain.service.ReportService;
import br.com.cesarschool.presentation.dto.report.ReportRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // POST /reports
    @PostMapping
    public ResponseEntity<String> sendReport(@RequestBody ReportRequest request) {
        reportService.sendReport(
            request.getStudentId(),
            request.getMonitorId(),
            request.getAttendanceDate(),
            request.getSubject(),
            request.getDescription()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body("Relato enviado com sucesso.");
    }

}
