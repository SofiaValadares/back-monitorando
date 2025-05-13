package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;

import java.util.List;

public class MonitorMapper {

    public static MonitorEntity toDomain(MonitorJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return new MonitorEntity(
                jpaEntity.getId(),
                jpaEntity.getUser().getName(),
                jpaEntity.getUser().getSurname(),
                jpaEntity.getUser().getEmail(),
                jpaEntity.getUser().getPassword(),
                jpaEntity.getUser().getRole(),
                List.of(),
                AvailableTimeMapper.toEntityList(jpaEntity.getAvailableTimes()),
                DisciplineMapper.toDomain(jpaEntity.getDiscipline())
        );
    }

    public static MonitorJpaEntity toJpa(MonitorEntity domainEntity) {
        if (domainEntity == null) return null;

        return new MonitorJpaEntity(
                domainEntity.getId(),
                UserEntityMapper.toJpaEntity(domainEntity), // Usa os dados de MonitorEntity como User
                DisciplineMapper.toJpa(domainEntity.getDisciplineMonitor()),
                AvailableTimeMapper.toJpaList(domainEntity.getAvailableTimes())
        );
    }
}
