package com.example.monitorando.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monitorando.entity.NotificationEntity;
import com.example.monitorando.entity.StudentEntity;
import com.example.monitorando.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	@Autowired private final NotificationRepository notificationRepository;

    public void notifyStudent(StudentEntity student, String message) {
        NotificationEntity notification = new NotificationEntity();
        notification.setStudent(student);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);

        notificationRepository.save(notification);
    }

    public List<NotificationEntity> getNotificationsForStudent(Long studentId) {
        return notificationRepository.findByStudentIdOrderByCreatedAtDesc(studentId);
    }

    public void markAsRead(Long notificationId) {
        Optional<NotificationEntity> optional = notificationRepository.findById(notificationId);
        optional.ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }
}
