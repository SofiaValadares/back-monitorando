package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.repository.user.AvaliableTimeRepository ;
import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Service
public class AvailableTimeService {

    private final AvaliableTimeRepository availableTimeRepository;
    private final NotificationService notificationService;

    public AvailableTimeService(AvaliableTimeRepository availableTimeRepository, NotificationService notificationService) {
        this.availableTimeRepository = availableTimeRepository;
        this.notificationService = notificationService;
    }

    public AvailableTimeEntity defineAvailableTime(AvailableTimeEntity newTime, Long monitorId) {
        // Validações de horário
        if (newTime.getStartTime().isAfter(newTime.getEndTime()) ||
                newTime.getStartTime().equals(newTime.getEndTime())) {
            throw new IllegalArgumentException("Horário inválido: o horário de início deve ser antes do término.");
        }

        LocalTime inicioPermitido = LocalTime.of(8, 0);
        LocalTime fimPermitido = LocalTime.of(22, 0);

        if (newTime.getStartTime().isBefore(inicioPermitido) ||
                newTime.getEndTime().isAfter(fimPermitido)) {
            throw new IllegalArgumentException("Horário inválido: deve estar entre 08:00 e 22:00.");
        }

        Duration duracao = Duration.between(newTime.getStartTime(), newTime.getEndTime());

        if (duracao.toMinutes() < 30) {
            throw new IllegalArgumentException("Horário inválido: duração mínima de 30 minutos.");
        }

        if (duracao.toHours() > 2 || (duracao.toHours() == 2 && duracao.toMinutesPart() > 0)) {
            throw new IllegalArgumentException("Horário inválido: duração máxima de 2 horas.");
        }

        // Conflito com horários já existentes
        List<AvailableTimeEntity> existingTimes = availableTimeRepository.findByMonitorId(monitorId);

        boolean hasConflict = existingTimes.stream().anyMatch(existing ->
                existing.getWeekDay() == newTime.getWeekDay() &&
                        !(newTime.getEndTime().isBefore(existing.getStartTime()) ||
                                newTime.getStartTime().isAfter(existing.getEndTime()))
        );

        if (hasConflict) {
            throw new IllegalArgumentException("Horário conflitante com outro já existente.");
        }

        // Salva o horário
        AvailableTimeEntity saved = availableTimeRepository.save(newTime, monitorId);

        // Recupera a lista atualizada de horários e o monitor para notificação
        List<AvailableTimeEntity> horariosAtualizados = availableTimeRepository.findByMonitorId(monitorId);
        MonitorEntity monitor = existingTimes.isEmpty() ? null : ((MonitorEntity) null); // <-- você precisará obter o monitor

        // Aqui você precisa recuperar o MonitorEntity completo
        // Dependendo da arquitetura, você pode ter um FindMonitorPort ou outro meio de acessar isso

        if (monitor != null) {
            notificationService.notifyStudentsOfUpdatedSchedule(monitor, horariosAtualizados);
        }

        return saved;
    }

    public void removeAvailableTimeById(Long id) {
        availableTimeRepository.deleteById(id);
    }

    public List<AvailableTimeEntity> getAvailableTimesByMonitor(Long monitorId) {
        return availableTimeRepository.findByMonitorId(monitorId);
    }
}
