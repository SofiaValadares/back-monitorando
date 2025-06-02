package br.com.cesarschool.infrastructure.adapter.user;


import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.repository.user.FindMonitorRepository;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.MonitorMapper;
import br.com.cesarschool.infrastructure.repository.MonitorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindMonitorAdapter implements FindMonitorRepository<MonitorEntity> {
    private final MonitorJpaRepository monitorJpaRepository;

    public FindMonitorAdapter(MonitorJpaRepository monitorJpaRepository) {
        this.monitorJpaRepository = monitorJpaRepository;
    }

    @Override
    public Optional<MonitorEntity> findById(Long id) {
        Optional<MonitorJpaEntity> monitorOptional = monitorJpaRepository.findById(id);

        if (monitorOptional.isPresent()) {
            MonitorJpaEntity monitorJpa = monitorOptional.get();
            return Optional.of(MonitorMapper.toDomain(monitorJpa));
        }

        return Optional.empty();
    }
}

