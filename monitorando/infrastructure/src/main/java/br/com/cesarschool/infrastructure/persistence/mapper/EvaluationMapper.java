package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.EvaluationEntity;
import br.com.cesarschool.infrastructure.persistence.entity.EvaluationJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;

public class EvaluationMapper {

    public static EvaluationJpaEntity toJpa(EvaluationEntity entity, MonitorJpaEntity monitor,
     StudentJpaEntity student) {
        return new EvaluationJpaEntity(
                null,
                entity.getAttendanceDate(),
                entity.getRating(),
                entity.getComment(),
                monitor,
                student
        );
    }

    public static EvaluationEntity toDomain(EvaluationJpaEntity jpa) {
        return new EvaluationEntity(
                jpa.getStudent() != null ? jpa.getStudent().getId() : null,
                jpa.getMonitor() != null ? jpa.getMonitor().getId() : null,
                jpa.getAttendanceDate(),
                jpa.getRating(),
                jpa.getComment()
        );
    }
}
