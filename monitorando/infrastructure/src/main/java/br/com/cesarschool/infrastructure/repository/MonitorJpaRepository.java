package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonitorJpaRepository extends JpaRepository<MonitorJpaEntity, Long> {
    Optional<MonitorJpaEntity> findById(Long id);
}
