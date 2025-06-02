package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.domain.repository.user.AvaliableTimeRepository;
import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.infrastructure.persistence.entity.AvailableTimeJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.AvailableTimeMapper;
import br.com.cesarschool.infrastructure.repository.AvailableTimeJpaRepository;
import br.com.cesarschool.infrastructure.repository.MonitorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvailableTimeAdapter implements AvaliableTimeRepository{

    private final AvailableTimeJpaRepository availableTimeJpaRepository;
    private final MonitorJpaRepository monitorJpaRepository;

    public AvailableTimeAdapter(AvailableTimeJpaRepository availableTimeJpaRepository,
                                MonitorJpaRepository monitorJpaRepository) {
        this.availableTimeJpaRepository = availableTimeJpaRepository;
        this.monitorJpaRepository = monitorJpaRepository;
    }

    @Override
    public AvailableTimeEntity save(AvailableTimeEntity availableTime, Long monitorId) {
        MonitorJpaEntity monitor = monitorJpaRepository.findById(monitorId)
                .orElseThrow(() -> new RuntimeException("Monitor não encontrado com ID: " + monitorId));

        AvailableTimeJpaEntity jpa = AvailableTimeMapper.toJpa(availableTime);
        jpa.setMonitorForSchedule(monitor); // relação de monitor → horário disponível

        AvailableTimeJpaEntity saved = availableTimeJpaRepository.save(jpa);
        return AvailableTimeMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        availableTimeJpaRepository.deleteById(id);
    }

    @Override
    public List<AvailableTimeEntity> findByMonitorId(Long monitorId) {
        List<AvailableTimeJpaEntity> horarios = availableTimeJpaRepository.findByMonitorForScheduleId(monitorId);
        return horarios.stream()
                .map(AvailableTimeMapper::toDomain)
                .collect(Collectors.toList());
    }
}
