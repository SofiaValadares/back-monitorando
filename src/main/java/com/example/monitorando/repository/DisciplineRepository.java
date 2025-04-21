package com.example.monitorando.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.monitorando.entity.DisciplineEntity;

public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Long> {

}
