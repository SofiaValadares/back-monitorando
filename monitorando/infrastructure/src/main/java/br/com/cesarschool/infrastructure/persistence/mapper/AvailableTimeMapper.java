package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.infrastructure.persistence.entity.AvailableTimeJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;

public class AvailableTimeMapper {

    public static AvailableTimeJpaEntity toJpa(AvailableTimeEntity entity, MonitorJpaEntity monitor) {
        return new AvailableTimeJpaEntity(
            entity.getId(),
            entity.getWeekDay(),
            entity.getStartTime(),
            entity.getEndTime(),
            monitor
        );
    }

    public static AvailableTimeEntity toDomain(AvailableTimeJpaEntity jpaEntity) {
        return new AvailableTimeEntity(
            jpaEntity.getId(),
            jpaEntity.getWeekDay(),
            jpaEntity.getStartTime(),
            jpaEntity.getEndTime()
        );
    }
}
