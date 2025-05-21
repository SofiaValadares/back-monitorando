package br.com.cesarschool.domain.repository.question;

public interface QuestionChatRepository {
    void sendAnswerQuestion(Long questionId, Long userId, String answer);
}
