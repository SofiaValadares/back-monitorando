package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
public class DisciplineMapper {

    public static DisciplineJpaEntity toJpa(DisciplineEntity entity) {
        return new DisciplineJpaEntity(
            entity.getId(),
            entity.getName(),
            entity.getCode(),
            null
        );
    }

    public static DisciplineEntity toDomain(DisciplineJpaEntity jpa) {
        return new DisciplineEntity(
            jpa.getId(),
            jpa.getName(),
            jpa.getCode()
        );
    }
}
