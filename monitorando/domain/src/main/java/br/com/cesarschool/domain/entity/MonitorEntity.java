package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorEntity extends StudentEntity {
    private List<AvailableTimeEntity> availableTimes;

    private DisciplineEntity disciplineMonitor;

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
        this.availableTimes = availableTimes;
        this.disciplineMonitor = disciplineMonitor;
    }
}
