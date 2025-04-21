package com.example.monitorando.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "discipline")
@Data
public class DisciplineEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 20)
    private String semester;
}
