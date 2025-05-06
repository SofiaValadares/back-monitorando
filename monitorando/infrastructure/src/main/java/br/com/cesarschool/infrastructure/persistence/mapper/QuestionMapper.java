package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionJpaEntity;

public class QuestionMapper {

    public static QuestionEntity toDomain(QuestionJpaEntity jpa) {
        if (jpa == null) return null;

        return new QuestionEntity(
            jpa.getId(),
            jpa.getQuestion(),
            StudentMapper.toDomain(jpa.getStudent()),
            DisciplineMapper.toDomain(jpa.getDiscipline()),
            jpa.getIsPublic(),
            jpa.getMonitor() != null ? MonitorMapper.toDomain(jpa.getMonitor()) : null
        );
    }

    public static QuestionJpaEntity toJpa(QuestionEntity domain) {
        if (domain == null) return null;

        return new QuestionJpaEntity(
            domain.getId(),
            domain.getQuestion(),
            StudentMapper.toJpa(domain.getStudent()),
            DisciplineMapper.toJpa(domain.getDiscipline()),
            domain.getIsPublic(),
            domain.getMonitor() != null ? MonitorMapper.toJpa(domain.getMonitor()) : null
        );
    }
}
