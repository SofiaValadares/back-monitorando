package br.com.cesarschool.infrastructure.persistence.mapper;


import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.infrastructure.persistence.entity.AvailableTimeJpaEntity;

public class AvailableTimeMapper {

    public static AvailableTimeEntity toDomain(AvailableTimeJpaEntity jpa) {
        return new AvailableTimeEntity(
                jpa.getId(),
                jpa.getDayOfWeek(),
                jpa.getStartTime(),
                jpa.getEndTime()
        );
    }

    /*public static AvailableTimeJpaEntity toJpa(AvailableTimeEntity entity) {
        AvailableTimeJpaEntity jpa = new AvailableTimeJpaEntity();
        jpa.setId(entity.getId());
        jpa.setDayOfWeek(entity.getDayOfWeek());
        jpa.setStartTime(entity.getStartTime());
        jpa.setEndTime(entity.getEndTime());
        return jpa;
    }*/
}
