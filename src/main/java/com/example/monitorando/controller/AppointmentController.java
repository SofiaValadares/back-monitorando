package com.example.monitorando.controller;

import com.example.monitorando.entity.AppointmentEntity;
import com.example.monitorando.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentEntity> create(@RequestBody AppointmentEntity appointment) {
        return ResponseEntity.ok(appointmentService.requestAppointment(appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentEntity> getById(@PathVariable Long id) {
        return appointmentService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AppointmentEntity>> getAll() {
        return ResponseEntity.ok(appointmentService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
