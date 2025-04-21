package com.example.monitorando.repository;

import com.example.monitorando.entity.DisciplineEntity;
import com.example.monitorando.entity.StudentEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
	 List<StudentEntity> findAllByDiscipline(DisciplineEntity discipline);
}