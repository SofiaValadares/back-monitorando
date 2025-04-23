package com.cesarschool.monitorando.persistencia.repository;

import com.cesarschool.monitorando.dominio.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
