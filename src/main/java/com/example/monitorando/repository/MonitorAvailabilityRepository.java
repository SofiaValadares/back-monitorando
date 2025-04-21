package com.example.monitorando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.monitorando.entity.MonitorAvailabilityEntity;


public interface MonitorAvailabilityRepository extends JpaRepository<MonitorAvailabilityEntity, Long> {
}

