package br.com.cesarschool.domain.repository.evaluation;

import br.com.cesarschool.domain.entity.EvaluationEntity;

import java.util.List;

public interface FindEvaluationsByStudentPort {
    List<EvaluationEntity> findByStudentId(Long studentId);
}
