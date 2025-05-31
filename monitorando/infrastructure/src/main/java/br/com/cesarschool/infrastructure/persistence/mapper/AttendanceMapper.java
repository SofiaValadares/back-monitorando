package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.AttendanceEntity;
import br.com.cesarschool.infrastructure.persistence.entity.AttendanceJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;

public class AttendanceMapper {

    public static AttendanceEntity toDomain(AttendanceJpaEntity jpa) {
        return new AttendanceEntity(
            jpa.getId(),
            jpa.getStudent() != null ? jpa.getStudent().getId() : null,
            jpa.getMonitor() != null ? jpa.getMonitor().getId() : null,
            jpa.getScheduledDateTime(),
            AttendanceEntity.Status.valueOf(jpa.getStatus().name())
        );
    }

    public static AttendanceJpaEntity toJpa(AttendanceEntity domain, StudentJpaEntity student, MonitorJpaEntity monitor) {
        return new AttendanceJpaEntity(
            domain.getId(),
            domain.getScheduledDateTime(),
            AttendanceJpaEntity.Status.valueOf(domain.getStatus().name()),
            student,
            monitor
        );
    }
}
