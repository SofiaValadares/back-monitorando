package com.cesarschool.monitorando.persistencia.repository;

import com.cesarschool.monitorando.dominio.entity.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepository extends JpaRepository<MonitorEntity, Long> {}