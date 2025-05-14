package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.application.port.user.FindMonitorPort;
import br.com.cesarschool.application.port.user.FindStudentPort;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.MonitorMapper;
import br.com.cesarschool.infrastructure.persistence.mapper.StudentMapper;
import br.com.cesarschool.infrastructure.repository.MonitorJpaRepository;
import br.com.cesarschool.infrastructure.repository.StudentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindMonitorAdapter implements FindMonitorPort<MonitorEntity> {
    MonitorJpaRepository monitorJpaRepository;

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

