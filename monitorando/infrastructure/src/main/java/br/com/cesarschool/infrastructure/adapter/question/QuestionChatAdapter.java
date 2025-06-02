package br.com.cesarschool.infrastructure.adapter.question;

import br.com.cesarschool.domain.repository.question.QuestionChatRepository;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionChatJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;
import br.com.cesarschool.infrastructure.repository.QuestionChatJpaRepository;
import br.com.cesarschool.infrastructure.repository.QuestionJpaRepository;
import br.com.cesarschool.infrastructure.repository.UserJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class QuestionChatAdapter implements QuestionChatRepository {
    private final UserJpaRepository userRepository;
    private final QuestionJpaRepository questionRepository;
    private final QuestionChatJpaRepository questionChatJpaRepository;

    public QuestionChatAdapter(UserJpaRepository userRepository, QuestionJpaRepository questionRepository, QuestionChatJpaRepository questionChatJpaRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.questionChatJpaRepository = questionChatJpaRepository;
    }

    @Override
    public void sendAnswerQuestion(Long questionId, Long userId, String answer) {
        QuestionJpaEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        UserJpaEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        QuestionChatJpaEntity chat = new QuestionChatJpaEntity();
        chat.setQuestion(question);
        chat.setUser(user);
        chat.setAnswer(answer);

        questionChatJpaRepository.save(chat);
    }
}
