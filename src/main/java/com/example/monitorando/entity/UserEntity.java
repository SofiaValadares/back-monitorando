package com.example.monitorando.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    private String password;
    private String institution;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean isActive = true;

    public enum Role {
        aluno, monitor, professor
    }
}
