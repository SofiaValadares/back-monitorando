package br.com.cesarschool.domain.repository.question;

import java.util.Optional;

public interface FindQuestionRepository<T> {
    Optional<T> findById(Long id);
}
