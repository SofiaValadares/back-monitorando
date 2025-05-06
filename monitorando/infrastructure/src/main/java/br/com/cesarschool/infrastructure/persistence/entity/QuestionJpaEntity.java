package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
public class QuestionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentJpaEntity student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discipline_id", nullable = false)
    private DisciplineJpaEntity discipline;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic;

    @ManyToOne
    @JoinColumn(name = "monitor_id")
    private MonitorJpaEntity monitor;
}
