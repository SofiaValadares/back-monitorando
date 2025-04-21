package com.example.monitorando.repository;

import com.example.monitorando.entity.MonitorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepository extends JpaRepository<MonitorEntity, Long> {}