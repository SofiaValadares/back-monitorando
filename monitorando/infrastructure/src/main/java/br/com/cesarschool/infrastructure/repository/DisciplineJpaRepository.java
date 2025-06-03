package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplineJpaRepository extends JpaRepository<DisciplineJpaEntity, Long> {
     Optional<DisciplineJpaEntity> findById(Long id);
     Optional<DisciplineJpaEntity> findByCode(String code);
     List<DisciplineJpaEntity> findByStudents_Id(Long studentId);
}
