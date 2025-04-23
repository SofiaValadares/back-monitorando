package com.cesarschool.monitorando.apresentacao.controller;

import com.cesarschool.monitorando.dominio.entity.NotificationEntity;
import com.cesarschool.monitorando.apresentacao.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
