package com.example.monitorando.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monitorando.DTO.MonitorAvailabilityRequest;
import com.example.monitorando.service.MonitorAvailabilityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/availability")
@RequiredArgsConstructor
public class MonitorAvailabilityController {

    private MonitorAvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<?> updateAvailability(@RequestBody MonitorAvailabilityRequest request) {
        try {
            availabilityService.updateAvailability(request);
            return ResponseEntity.ok("Horários atualizados e alunos notificados.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
