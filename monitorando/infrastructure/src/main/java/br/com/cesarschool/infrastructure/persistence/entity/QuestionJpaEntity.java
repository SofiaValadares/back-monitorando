package br.com.cesarschool.infrastructure.persistence.entity;

import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionStatus status = QuestionStatus.PENDING;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionChatJpaEntity> chats;
}
