package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DisciplineMapper {

    public static DisciplineJpaEntity toJpa(DisciplineEntity entity) {
        return new DisciplineJpaEntity(
                entity.getName(),
                entity.getCode()
        );
    }

    public static DisciplineEntity toDomain(DisciplineJpaEntity jpa) {
        List<StudentEntity> studentEntities = jpa.getStudents() == null ? List.of() :
                jpa.getStudents().stream()
                        .map(studentJpa -> new StudentEntity(
                                UserEntityMapper.toDomainEntity(studentJpa.getUser()),
                                null
                        ))
                        .collect(Collectors.toList());

        return new DisciplineEntity(
                jpa.getId(),
                jpa.getName(),
                jpa.getCode(),
                studentEntities,
                null
        );
    }

    public static List<DisciplineEntity> toDomainList(List<DisciplineJpaEntity> jpaList) {
        if (jpaList == null) return List.of();
        return jpaList.stream()
                .map(DisciplineMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<DisciplineJpaEntity> toJpaList(List<DisciplineEntity> entityList) {
        if (entityList == null) return List.of();
        return entityList.stream()
                .map(DisciplineMapper::toJpa)
                .collect(Collectors.toList());
    }
}
