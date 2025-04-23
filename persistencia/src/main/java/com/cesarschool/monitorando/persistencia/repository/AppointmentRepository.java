package com.cesarschool.monitorando.persistencia.repository;


import com.cesarschool.monitorando.dominio.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findByMonitorIdAndAppointmentDate(Long monitorId, LocalDate appointmentDate);
}
