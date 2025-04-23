package com.cesarschool.monitorando.dominio.service;

import com.cesarschool.monitorando.apresentacao.DTO.MonitorAvailabilityRequest;
import com.cesarschool.monitorando.dominio.entity.MonitorAvailabilityEntity;
import com.cesarschool.monitorando.dominio.entity.MonitorEntity;
import com.cesarschool.monitorando.dominio.entity.StudentEntity;
import com.cesarschool.monitorando.persistencia.repository.MonitorAvailabilityRepository;
import com.cesarschool.monitorando.persistencia.repository.MonitorRepository;
import com.cesarschool.monitorando.persistencia.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonitorAvailabilityService {

	@Autowired private MonitorAvailabilityRepository availabilityRepository;
	@Autowired private MonitorRepository monitorRepository;
	@Autowired private StudentRepository studentRepository;
	@Autowired private NotificationService notificationService;

    public void updateAvailability(MonitorAvailabilityRequest request) {
        MonitorEntity monitor = monitorRepository.findById(request.getMonitorId())
                .orElseThrow(() -> new IllegalArgumentException("Monitor não encontrado."));

        for (MonitorAvailabilityRequest.AvailabilityDTO dto : request.getAvailability()) {
            if (!isValidTime(dto.getStartTime()) || !isValidTime(dto.getEndTime())) {
                throw new IllegalArgumentException("Horário inválido: " + dto.getStartTime() + " - " + dto.getEndTime());
            }

            // Aqui você pode validar se o horário está dentro de um intervalo permitido, como 08:00 - 22:00
        }

        // Salvar todos os horários
        List<MonitorAvailabilityEntity> entities = request.getAvailability().stream().map(dto -> {
            MonitorAvailabilityEntity entity = new MonitorAvailabilityEntity();
            entity.setMonitor(monitor);
            entity.setDayOfWeek(dto.getDayOfWeek());
            entity.setStartTime(dto.getStartTime());
            entity.setEndTime(dto.getEndTime());
            return entity;
        }).toList();

        availabilityRepository.saveAll(entities);

        // Notificar alunos das disciplinas do monitor
        List<StudentEntity> alunos = studentRepository.findAllByDiscipline(monitor.getDiscipline());
        alunos.forEach(aluno -> notificationService.notifyUser(aluno, "O monitor atualizou seus horários disponíveis!"));
    }

    private boolean isValidTime(String time) {
        return time.matches("^([01]\\d|2[0-3]):[0-5]\\d$"); // HH:mm (24h)
    }
}
