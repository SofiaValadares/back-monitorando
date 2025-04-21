package com.example.monitorando.service;

import com.example.monitorando.entity.AppointmentEntity;
import com.example.monitorando.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentEntity save(AppointmentEntity appointment) {
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
