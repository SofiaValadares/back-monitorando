package com.cesarschool.monitorando.dominio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "monitor_availability")
@Data
public class MonitorAvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "monitor_id")
    private MonitorEntity monitor;

    @Column(nullable = false)
    private String dayOfWeek; // "Segunda", "Terça", etc.

    @Column(nullable = false)
    private String startTime; // formato "HH:mm"

    @Column(nullable = false)
    private String endTime; // formato "HH:mm"

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
