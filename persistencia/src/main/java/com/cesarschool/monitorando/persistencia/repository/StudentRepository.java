package com.cesarschool.monitorando.persistencia.repository;


import java.util.List;

import com.cesarschool.monitorando.dominio.entity.DisciplineEntity;
import com.cesarschool.monitorando.dominio.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
	 List<StudentEntity> findAllByDiscipline(DisciplineEntity discipline);
}