package com.cesarschool.monitorando.persistencia.repository;

import com.cesarschool.monitorando.dominio.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {
}
