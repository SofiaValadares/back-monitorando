package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question_chat")
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

    public QuestionChatJpaEntity() {
    }

    public QuestionChatJpaEntity(Long id, QuestionJpaEntity question, UserJpaEntity user, String answer) {
        this.id = id;
        this.question = question;
        this.user = user;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionJpaEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionJpaEntity question) {
        this.question = question;
    }

    public UserJpaEntity getUser() {
        return user;
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
