package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "students")
public class StudentJpaEntity{

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private UserJpaEntity user;

    @ManyToMany
    @JoinTable(
            name = "student_discipline",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private List<DisciplineJpaEntity> disciplines;

    public StudentJpaEntity() {}

    public StudentJpaEntity(Long id, UserJpaEntity user, List<DisciplineJpaEntity> disciplines) {
        this.id = id;
        this.user = user;
        this.disciplines = disciplines;
    }

    public Long getId() {
        return id;
    }

    public List<DisciplineJpaEntity> getDisciplines() {
        return disciplines;
    }

    public UserJpaEntity getUser() {
        return user;
    }

    public void addDiscipline(DisciplineJpaEntity discipline) {
        disciplines.add(discipline);
    }
}
