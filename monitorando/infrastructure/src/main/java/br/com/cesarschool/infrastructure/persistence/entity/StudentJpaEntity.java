package br.com.cesarschool.infrastructure.persistence.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "students")
@Getter
@NoArgsConstructor
public class StudentJpaEntity extends UserJpaEntity {

    @ManyToMany
    @JoinTable(
            name = "student_discipline",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private List<DisciplineJpaEntity> disciplines;
    public StudentJpaEntity(Long id, String name, String surname, String email, String password, List<DisciplineJpaEntity> disciplines) {
        super(id, name, surname, email, password, UserRole.STUDENT);
        this.disciplines = disciplines;
    }
}
