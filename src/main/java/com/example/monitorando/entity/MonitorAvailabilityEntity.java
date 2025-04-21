package com.example.monitorando.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

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
