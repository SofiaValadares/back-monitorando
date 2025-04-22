package com.example.monitorando.service;

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
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    public AppointmentEntity requestAppointment(AppointmentEntity appointment) {
        Long studentId = appointment.getStudent().getId();
        Long monitorId = appointment.getMonitor().getId();
        Long disciplineId = appointment.getDiscipline().getId();

        // Validação de existência
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

        // todo: notify aluno e monitor
        return appointmentRepository.save(appointment);
    }

    public Optional<AppointmentEntity> getById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<AppointmentEntity> getAll() {
        return appointmentRepository.findAll();
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
