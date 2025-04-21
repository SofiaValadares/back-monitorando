package com.example.monitorando.repository;

import com.example.monitorando.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {
}
