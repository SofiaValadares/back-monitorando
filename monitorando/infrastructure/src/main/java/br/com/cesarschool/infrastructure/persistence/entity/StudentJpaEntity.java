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
@AllArgsConstructor
public class StudentJpaEntity{

    @Id
    private Long id;

    @OneToOne
    @MapsId // compartilha o mesmo ID do User
    @JoinColumn(name = "id")
    private UserJpaEntity user;

    @ManyToMany
    @JoinTable(
            name = "student_discipline",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private List<DisciplineJpaEntity> disciplines;
}
