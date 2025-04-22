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

    @Autowired
    private NotificationService notificationService;

    // Buscar todas as notificações de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationEntity>> getNotifications(@PathVariable Long userId) {
        List<NotificationEntity> notifications = notificationService.getNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    // Marcar uma notificação como lida
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok().build();
    }
}
