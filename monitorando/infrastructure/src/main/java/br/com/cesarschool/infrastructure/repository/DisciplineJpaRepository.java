package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplineJpaRepository extends JpaRepository<DisciplineJpaEntity, Long> {
    public Optional<DisciplineJpaEntity> findById(Long id);
}
