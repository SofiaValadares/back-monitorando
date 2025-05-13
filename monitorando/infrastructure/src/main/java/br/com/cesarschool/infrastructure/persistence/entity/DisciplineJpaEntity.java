package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "disciplines")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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


    public DisciplineJpaEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
