package com.example.monitorando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monitorando.DTO.MonitorAvailabilityRequest;
import com.example.monitorando.entity.MonitorAvailabilityEntity;
import com.example.monitorando.entity.MonitorEntity;
import com.example.monitorando.entity.StudentEntity;
import com.example.monitorando.repository.MonitorAvailabilityRepository;
import com.example.monitorando.repository.MonitorRepository;
import com.example.monitorando.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

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
        List< MonitorAvailabilityEntity> entities = request.getAvailability().stream().map(dto -> {
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
        alunos.forEach(aluno -> notificationService.notifyStudent(aluno, "O monitor atualizou seus horários disponíveis!"));
    }

    private boolean isValidTime(String time) {
        return time.matches("^([01]\\d|2[0-3]):[0-5]\\d$"); // HH:mm (24h)
    }
}
