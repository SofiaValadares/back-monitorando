package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.AvailableTimeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailableTimeJpaRepository extends JpaRepository<AvailableTimeJpaEntity, Long> {

    List<AvailableTimeJpaEntity> findByMonitorForScheduleId(Long monitorId);
}
