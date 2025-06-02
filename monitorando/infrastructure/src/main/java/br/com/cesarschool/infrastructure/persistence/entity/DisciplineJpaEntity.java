package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "disciplines")
public class DisciplineJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToMany(mappedBy = "disciplines")
    private List<StudentJpaEntity> students;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonitorJpaEntity> monitors;

    public DisciplineJpaEntity() {}

    public DisciplineJpaEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public DisciplineJpaEntity(Long id, String name, String code, List<StudentJpaEntity> students, List<MonitorJpaEntity> monitors) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.students = students;
        this.monitors = monitors;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<StudentJpaEntity> getStudents() {
        return students;
    }

    public List<MonitorJpaEntity> getMonitors() {
        return monitors;
    }
}
