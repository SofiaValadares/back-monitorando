package br.com.cesarschool.infrastructure.adapter.question;

import br.com.cesarschool.application.port.question.QuestionChatPort;

public class QuestionChatAdapter implements QuestionChatPort {
    @Override
    public void sendAnswerQuestion(Long questionId, Long userId, String answer) {
        // TODO
    }
}
