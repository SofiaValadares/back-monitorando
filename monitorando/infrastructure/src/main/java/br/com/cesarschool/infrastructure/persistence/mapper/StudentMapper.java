package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentJpaEntity toJpa(StudentEntity entity) {
        UserJpaEntity user = new UserJpaEntity();
        user.setId(entity.getId());

        return new StudentJpaEntity(
                entity.getId(),
                user,
                List.of()
        );
    }


    public static StudentEntity toDomain(StudentJpaEntity jpa) {
        List<DisciplineEntity> disciplineEntities = jpa.getDisciplines() != null
                ? jpa.getDisciplines().stream()
                .map(DisciplineMapper::toDomain)
                .collect(Collectors.toList())
                : List.of();

        List<Long> disciplineIds = disciplineEntities.stream()
                .map(DisciplineEntity::getId)
                .collect(Collectors.toList());

    return new StudentEntity(
                jpa.getId(),
                jpa.getUser().getName(),
                jpa.getUser().getSurname(),
                jpa.getUser().getEmail(),
                jpa.getUser().getPassword(),
                jpa.getUser().getRole(),
                disciplineIds
        );
    }
}
