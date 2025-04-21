package com.example.monitorando.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "question")
@Data
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discipline_id")
    private DisciplineEntity discipline;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_public")
    private Boolean isPublic = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.open;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status {
        open, answered, closed
    }
}
