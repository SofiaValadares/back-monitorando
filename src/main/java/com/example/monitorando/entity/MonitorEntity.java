package com.example.monitorando.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "monitor")
@Data
public class MonitorEntity extends UserEntity {

    @Column(length = 50)
    private String time;

    @Column(length = 20)
    private String semester;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discipline_id", nullable = false)
    private DisciplineEntity discipline;
}
