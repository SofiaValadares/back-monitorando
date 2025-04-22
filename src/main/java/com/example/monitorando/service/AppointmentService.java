package com.example.monitorando.service;

import com.example.monitorando.DTO.AppointmentDTO;
import com.example.monitorando.entity.AppointmentEntity;
import com.example.monitorando.repository.AppointmentRepository;
import com.example.monitorando.repository.StudentRepository;
import com.example.monitorando.repository.MonitorRepository;
import com.example.monitorando.repository.DisciplineRepository;
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