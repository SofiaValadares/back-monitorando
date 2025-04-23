package com.cesarschool.monitorando.apresentacao.service;

import com.cesarschool.monitorando.apresentacao.DTO.AppointmentDTO;
import com.cesarschool.monitorando.dominio.entity.AppointmentEntity;
import com.cesarschool.monitorando.persistencia.repository.AppointmentRepository;
import com.cesarschool.monitorando.persistencia.repository.DisciplineRepository;
import com.cesarschool.monitorando.persistencia.repository.MonitorRepository;
import com.cesarschool.monitorando.persistencia.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private NotificationService notificationService; // ✅ ADICIONADO

    public AppointmentDTO requestAppointment(AppointmentEntity appointment) {
        Long studentId = appointment.getStudent().getId();
        Long monitorId = appointment.getMonitor().getId();
        Long disciplineId = appointment.getDiscipline().getId();

        if (!studentRepository.existsById(studentId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estudante com ID " + studentId + " não existe.");
        }

        if (!monitorRepository.existsById(monitorId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Monitor com ID " + monitorId + " não existe.");
        }

        if (!disciplineRepository.existsById(disciplineId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Disciplina com ID " + disciplineId + " não existe.");
        }

        LocalDate appointmentDate = appointment.getAppointmentDate();
        LocalTime startTime = appointment.getStartTime();
        LocalTime endTime = appointment.getEndTime();

        List<AppointmentEntity> agendamentos = appointmentRepository
                .findByMonitorIdAndAppointmentDate(monitorId, appointmentDate);

        boolean isAvailable = agendamentos.stream().noneMatch(existing ->
                startTime.isBefore(existing.getEndTime()) && endTime.isAfter(existing.getStartTime())
        );

        if (!isAvailable) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O monitor está indisponível nesse horário.");
        }

        appointment.setStatus(AppointmentEntity.Status.pending_approval);
        appointment.setCreatedAt(LocalDateTime.now());
        AppointmentEntity saved = appointmentRepository.save(appointment);

        // ✅ Notificações
        notificationService.notifyUser(
                saved.getStudent(),
                "Seu pedido de agendamento foi enviado e está aguardando aprovação."
        );

        notificationService.notifyUser(
                saved.getMonitor(),
                "Você recebeu um novo pedido de atendimento para o dia " +
                        saved.getAppointmentDate() + " das " + saved.getStartTime() + " às " + saved.getEndTime() + "."
        );

        return toDTO(saved);
    }

    public Optional<AppointmentDTO> getById(Long id) {
        return appointmentRepository.findById(id).map(this::toDTO);
    }

    public List<AppointmentDTO> getAll() {
        return appointmentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentDTO toDTO(AppointmentEntity entity) {
        return new AppointmentDTO(
                entity.getId(),
                entity.getStudent().getId(),
                entity.getMonitor().getId(),
                entity.getDiscipline().getId(),
                entity.getAppointmentDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStatus().name(),
                entity.getCreatedAt()
        );
    }
}
