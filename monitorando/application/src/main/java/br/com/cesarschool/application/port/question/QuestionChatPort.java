package br.com.cesarschool.application.port.question;

public interface QuestionChatPort {
    void sendAnswerQuestion(Long questionId, Long userId, String answer);
}
