package br.com.cesarschool.infrastructure.persistence.mapper;

import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.UserJpaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MonitorMapper {

    public static MonitorJpaEntity toJpa(MonitorEntity entity) {
        UserJpaEntity user = new UserJpaEntity();
        user.setId(entity.getId());

        return new MonitorJpaEntity(
                entity.getId(),
                user,
                List.of(), // disciplinas (opcionalmente você pode mapear se necessário)
                List.of(), // horários disponíveis
                List.of()  // horários de monitoria
        );
    }

    public static MonitorEntity toDomain(MonitorJpaEntity jpa) {
        return new MonitorEntity(
                jpa.getId(),
                jpa.getUser().getName(),
                jpa.getUser().getSurname(),
                jpa.getUser().getEmail(),
                jpa.getUser().getPassword(),
                jpa.getUser().getRole(),
                jpa.getDisciplines() != null
                        ? jpa.getDisciplines().stream()
                        .map(DisciplineMapper::toDomain)
                        .collect(Collectors.toList())
                        : List.of(),
                null,
                null
                /*jpa.getAvailableTimes() != null
                        ? jpa.getAvailableTimes().stream()
                        .map(AvailableTimeMapper::toDomain)
                        .collect(Collectors.toList())
                        : List.of(),
                jpa.getMonitorSchedule() != null
                        ? jpa.getMonitorSchedule().stream()
                        .map(AvailableTimeMapper::toDomain)
                        .collect(Collectors.toList())
                        : List.of()*/
        );
    }
}
