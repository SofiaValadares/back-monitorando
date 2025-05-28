package br.com.cesarschool.domain.entity;

public class QuestionChatEntity {
    private Long id;
    private QuestionEntity question;
    private UserEntity user;
    private String answer;

    public QuestionChatEntity(Long id, QuestionEntity question, UserEntity user, String answer) {
        if (question == null) {
            throw new IllegalArgumentException("A pergunta (question) não pode ser nula.");
        }
        if (user == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        if (answer == null || answer.isBlank()) {
            throw new IllegalArgumentException("A resposta não pode estar vazia.");
        }

        this.id = id;
        this.question = question;
        this.user = user;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public UserEntity getUser() {
        return user;
    }

    public String getAnswer() {
        return answer;
    }
}
