package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.EvaluationJpaEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationJpaRepository extends JpaRepository<EvaluationJpaEntity, Long> {
	List<EvaluationJpaEntity> findByStudent_Id(Long studentId);
}
