package br.com.cesarschool.application.port.question;

import java.util.Optional;

public interface FindQuestionPort<T> {
    Optional<T> findById(Long id);
}
