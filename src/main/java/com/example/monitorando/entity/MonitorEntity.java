package com.example.monitorando.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "monitor")
@Data
public class MonitorEntity extends UserEntity {
    private String time;
    private String semester;
}
