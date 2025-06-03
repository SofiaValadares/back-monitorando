package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.QuestionEntity;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionJpaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    public static QuestionEntity toDomain(QuestionJpaEntity jpa) {
        if (jpa == null) return null;

        return new QuestionEntity(
            jpa.getId(),
            jpa.getQuestion(),
            jpa.getStudent().getId(),
            jpa.getDiscipline().getId(),
            jpa.getPublic(),
            jpa.getStatus(),
            jpa.getMonitor().getId()
        );
    }

    public static QuestionJpaEntity toJpa(QuestionEntity domain) {
        if (domain == null) return null;

        return new QuestionJpaEntity(
            domain.getId(),
            domain.getQuestion(),
            null,
            null,
            domain.getPublic(),
            null,
            domain.getStatus(),
            List.of()
        );
    }

    public static List<QuestionEntity> toDomainList(List<QuestionJpaEntity> questionJpaEntities) {
        if (questionJpaEntities == null) return null;

        return questionJpaEntities.stream()
                .map(QuestionMapper::toDomain)
                .collect(Collectors.toList());
    }
}
