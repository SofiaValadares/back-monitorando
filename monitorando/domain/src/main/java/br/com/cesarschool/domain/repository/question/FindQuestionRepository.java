package br.com.cesarschool.domain.repository.question;

import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.domain.entity.enums.QuestionStatus;

import java.util.List;
import java.util.Optional;

public interface FindQuestionRepository {
    Optional<QuestionEntity> findById(Long id);
    List<QuestionEntity> findByStudentId(Long id);
    List<QuestionEntity> findByStatusAndStudentId(QuestionStatus status, Long studentId);
}
