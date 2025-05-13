package br.com.cesarschool.infrastructure.persistence.entity;

import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.domain.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question_chat")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionChatJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionJpaEntity question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    private String answer;
}
