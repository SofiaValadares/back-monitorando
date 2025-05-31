package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.AttendanceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceJpaRepository extends JpaRepository<AttendanceJpaEntity, Long> {
    List<AttendanceJpaEntity> findByStudent_IdAndMonitor_Id(Long studentId, Long monitorId);
}
