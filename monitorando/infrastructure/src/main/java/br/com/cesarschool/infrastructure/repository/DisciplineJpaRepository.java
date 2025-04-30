package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineJpaRepository extends JpaRepository<DisciplineJpaEntity, Long> {
}
