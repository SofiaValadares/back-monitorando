package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.QuestionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionJpaRepository extends JpaRepository<QuestionJpaEntity, Long> {
}
