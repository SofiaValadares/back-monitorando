package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.ReportEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.ReportJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;

public class ReportMapper {

    public static ReportJpaEntity toJpa(ReportEntity entity, StudentJpaEntity student, MonitorJpaEntity monitor) {
        return new ReportJpaEntity(
            null, // ID será gerado automaticamente
            entity.getDate(),
            entity.getSubject(),
            entity.getDescription(),
            student,
            monitor
        );
    }

    public static ReportEntity toDomain(ReportJpaEntity jpa) {
        return new ReportEntity(
            jpa.getStudent() != null ? jpa.getStudent().getId() : null,
            jpa.getMonitor() != null ? jpa.getMonitor().getId() : null,
            jpa.getAttendanceDate(),
            jpa.getSubject(),
            jpa.getDescription()
        );
    }
}
