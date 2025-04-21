package com.example.monitorando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monitorando.entity.NotificationEntity;
import com.example.monitorando.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired private NotificationService notificationService;

    // Endpoint para buscar todas as notificações de um aluno
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<NotificationEntity>> getNotifications(@PathVariable Long studentId) {
        List<NotificationEntity> notifications = notificationService.getNotificationsForStudent(studentId);
        return ResponseEntity.ok(notifications);
    }

    // Endpoint para marcar uma notificação como lida
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.noContent().build();
    }
}
