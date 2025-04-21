package com.example.monitorando.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class StudentEntity extends UserEntity {
    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    private String course;
    private Integer semester;
}
