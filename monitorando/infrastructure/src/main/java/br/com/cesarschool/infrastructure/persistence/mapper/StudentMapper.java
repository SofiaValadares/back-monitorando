package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;

import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentJpaEntity toJpa(StudentEntity entity) {
        return new StudentJpaEntity(
            entity.getId(),
            entity.getName(),
            entity.getSurname(),
            entity.getEmail(),
            entity.getPassword(),
            entity.getDisciplines() != null ?
                entity.getDisciplines().stream()
                    .map(DisciplineMapper::toJpa)
                    .collect(Collectors.toList()) : null
        );
    }

    public static StudentEntity toDomain(StudentJpaEntity jpa) {
        return new StudentEntity(
            jpa.getId(),
            jpa.getName(),
            jpa.getSurname(),
            jpa.getEmail(),
            jpa.getPassword(),
            jpa.getRole(),
            jpa.getDisciplines() != null ?
                jpa.getDisciplines().stream()
                    .map(DisciplineMapper::toDomain)
                    .collect(Collectors.toList()) : null
        );
    }
}
