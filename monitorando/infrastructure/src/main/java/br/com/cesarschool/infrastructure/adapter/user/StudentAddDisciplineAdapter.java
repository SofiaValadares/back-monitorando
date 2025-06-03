package br.com.cesarschool.infrastructure.adapter.user;

import br.com.cesarschool.domain.repository.user.StudentAddDisciplineRepository;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.repository.DisciplineJpaRepository;
import br.com.cesarschool.infrastructure.repository.StudentJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class StudentAddDisciplineAdapter implements StudentAddDisciplineRepository {
    private final StudentJpaRepository studentJpaRepository;
    private final DisciplineJpaRepository disciplineJpaRepository;

    public StudentAddDisciplineAdapter(StudentJpaRepository studentJpaRepository, DisciplineJpaRepository disciplineJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
        this.disciplineJpaRepository = disciplineJpaRepository;
    }

    @Override
    public void addDiscipline(Long id, String code) {
        StudentJpaEntity student = studentJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));

        DisciplineJpaEntity discipline = disciplineJpaRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Discipline " + code + " not found"));

        student.addDiscipline(discipline);
        discipline.addStudent(student);

        studentJpaRepository.save(student);
        disciplineJpaRepository.save(discipline);
    }
}
