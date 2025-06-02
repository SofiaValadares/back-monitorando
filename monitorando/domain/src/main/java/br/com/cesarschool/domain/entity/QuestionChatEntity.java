package br.com.cesarschool.domain.entity;

public class QuestionChatEntity {
    private Long id;
    private Long questionId;
    private Long userId;
    private String answer;

    public QuestionChatEntity(Long id, Long questionId, Long userId, String answer) {
        if (questionId == null) {
            throw new IllegalArgumentException("A pergunta (question) não pode ser nula.");
        }
        if (userId == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("A resposta não pode estar vazia.");
        }

        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAnswer() {
        return answer;
    }
}
