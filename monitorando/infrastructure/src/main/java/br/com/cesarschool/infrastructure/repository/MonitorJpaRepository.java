package br.com.cesarschool.infrastructure.repository;

import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorJpaRepository extends JpaRepository<MonitorJpaEntity, Long> {
}
