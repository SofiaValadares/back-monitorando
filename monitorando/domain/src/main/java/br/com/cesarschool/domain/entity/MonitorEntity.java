package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;

import java.util.ArrayList;
import java.util.List;


public class MonitorEntity extends StudentEntity {
    private Long disciplineId;
    private List<Long> availableTimesIds = List.of();

    public MonitorEntity(
            StudentEntity student,
            Long disciplineId,
            List<Long> availableTimesIds
    ) {
        super(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPassword(),
                student.getRole(),
                student.getDisciplineIds()
        );

        if (disciplineId == null) {
            throw new IllegalArgumentException("Monitor deve estar associado a uma disciplina.");
        }

        this.disciplineId = disciplineId;

        if (availableTimesIds != null) {
            this.availableTimesIds = availableTimesIds;
        }

    }

    public MonitorEntity(
            Long id,
            String name,
            String surname,
            String email,
            String password,
            UserRole role,
            List<Long> disciplinesIds,
            Long disciplineId,
            List<Long> availableTimesIds
    ) {
        super(id, name, surname, email, password, role, disciplinesIds);

        if (disciplineId == null) {
            throw new IllegalArgumentException("Monitor deve estar associado a uma disciplina.");
        }

        this.disciplineId = disciplineId;

        if (availableTimesIds != null) {
            this.availableTimesIds = availableTimesIds;
        }
    }

    public Long getDisciplineId() { return disciplineId; }

    public List<Long> getAvailableTimesIds() { return availableTimesIds; }

    public void addAvailableTimesId(Long timesId) { availableTimesIds.add(timesId); }

    public void removeAvailableTimesId(Long timesId) { availableTimesIds.remove(timesId); }

}
