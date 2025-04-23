package com.cesarschool.monitorando.persistencia.repository;

import com.cesarschool.monitorando.dominio.entity.DisciplineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<DisciplineEntity, Long> {

}
