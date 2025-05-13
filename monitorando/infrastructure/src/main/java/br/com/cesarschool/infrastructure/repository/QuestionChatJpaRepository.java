package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.QuestionChatJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionChatJpaRepository extends JpaRepository<QuestionChatJpaEntity, Long> {
}
