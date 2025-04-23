package com.cesarschool.monitorando.persistencia.repository;

import com.cesarschool.monitorando.dominio.entity.MonitorAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorAvailabilityRepository extends JpaRepository<MonitorAvailabilityEntity, Long> {
}

