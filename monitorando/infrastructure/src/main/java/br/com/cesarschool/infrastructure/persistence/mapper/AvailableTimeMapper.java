package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.infrastructure.persistence.entity.AvailableTimeJpaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AvailableTimeMapper {

    public static AvailableTimeEntity toDomain(AvailableTimeJpaEntity jpa) {
        if (jpa == null) return null;

        return new AvailableTimeEntity(
                jpa.getId(),
                jpa.getDayOfWeek(),
                jpa.getStartTime(),
                jpa.getEndTime()
        );
    }

    public static AvailableTimeJpaEntity toJpa(AvailableTimeEntity entity) {
        if (entity == null) return null;

        return new AvailableTimeJpaEntity(
                entity.getId(),
                entity.getWeekDay(),
                entity.getStartTime(),
                entity.getEndTime(),
                null, // monitorForAttendance será setado externamente, se necessário
                null  // monitorForSchedule também
        );
    }

    public static List<AvailableTimeEntity> toEntityList(List<AvailableTimeJpaEntity> jpaList) {
        if (jpaList == null) return null;
        return jpaList.stream()
                .map(AvailableTimeMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<AvailableTimeJpaEntity> toJpaList(List<AvailableTimeEntity> entityList) {
        if (entityList == null) return null;
        return entityList.stream()
                .map(AvailableTimeMapper::toJpa)
                .collect(Collectors.toList());
    }
}
