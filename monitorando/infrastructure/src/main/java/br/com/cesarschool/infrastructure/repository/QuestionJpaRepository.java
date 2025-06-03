package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionJpaEntity, Long> {
    Optional<QuestionJpaEntity> findById(Long id);
    List<QuestionJpaEntity> findAllByStudent_Id(Long id);

    List<QuestionJpaEntity> findAllByStudent_IdAndStatus(Long id, QuestionStatus status);
}
