package br.com.cesarschool.domain.entity;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
public class DisciplineEntity {
    private final Long id;
    private final String name;
    private final String code;
    private final List<StudentEntity> students;
    private final List<MonitorEntity> monitors;

    public DisciplineEntity(Long id, String name, String code, List<StudentEntity> students, List<MonitorEntity> monitors) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O nome da disciplina não pode estar vazio.");
        }
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("O código da disciplina não pode estar vazio.");
        }

        this.id = id;
        this.name = name;
        this.code = code;
        this.students = students;
        this.monitors = monitors;
    }


    public Optional<UserEntity> findUserInDiscipline(Long userId) {
        if (students != null) {
            for (StudentEntity student : students) {
                if (student.getId().equals(userId)) {
                    return Optional.of(student);
                }
            }
        }

        if (monitors != null) {
            for (MonitorEntity monitor : monitors) {
                if (monitor.getId().equals(userId)) {
                    return Optional.of(monitor);
                }
            }
        }

        return Optional.empty();
    }
}
