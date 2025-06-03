package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;

import java.util.List;

public class MonitorMapper {

    public static MonitorEntity toDomain(MonitorJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        StudentEntity student = StudentMapper.toDomain(jpaEntity.getUser());

        return new MonitorEntity(
                student,
                jpaEntity.getDiscipline().getId(),
                List.of()
        );
    }

    public static MonitorJpaEntity toJpa(MonitorEntity domainEntity) {
        if (domainEntity == null) return null;


        return new MonitorJpaEntity(
                domainEntity.getId(),
                StudentMapper.toJpa(domainEntity),
                null,
                List.of()
        );
    }
}
