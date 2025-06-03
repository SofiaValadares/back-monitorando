package br.com.cesarschool.infrastructure.adapter.question;


import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.domain.repository.question.FindQuestionRepository;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.QuestionMapper;
import br.com.cesarschool.infrastructure.repository.QuestionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindQuestionAdapter implements FindQuestionRepository {
    private final QuestionJpaRepository questionRepository;

    public FindQuestionAdapter(QuestionJpaRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Optional<QuestionEntity> findById(Long id) {
        Optional<QuestionJpaEntity> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()) {
            QuestionJpaEntity question = questionOptional.get();
            return Optional.of(QuestionMapper.toDomain(question));
        }

        return Optional.empty();
    }

    @Override
    public List<QuestionEntity> findByStudentId(Long id) {
        return QuestionMapper.toDomainList(
                questionRepository.findAllByStudent_Id(id)
        );
    }
}
