package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;
import lombok.Getter;

import java.util.List;


public class MonitorEntity extends StudentEntity {
    private final List<AvailableTimeEntity> availableTimes;
    private final DisciplineEntity disciplineMonitor;

    public MonitorEntity(
            Long id,
            String name,
            String surname,
            String email,
            String password,
            UserRole role,
            List<DisciplineEntity> disciplines,
            List<AvailableTimeEntity> availableTimes,
            DisciplineEntity disciplineMonitor
    ) {
        super(id, name, surname, email, password, role, disciplines);

        if (disciplineMonitor == null) {
            throw new IllegalArgumentException("Monitor deve estar associado a uma disciplina.");
        }

        this.availableTimes = availableTimes;
        this.disciplineMonitor = disciplineMonitor;
    }

    public List<AvailableTimeEntity> getAvailableTimes() {
        return availableTimes;
    }

    public DisciplineEntity getDisciplineMonitor() {
        return disciplineMonitor;
    }
}
