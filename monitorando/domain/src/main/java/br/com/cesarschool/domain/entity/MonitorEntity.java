package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MonitorEntity extends StudentEntity{
    private DisciplineEntity discipline;
    private List<MonitorAvailabilityEntity> availabilities;

    public MonitorEntity(Long id, String name, String surname, String email, String password, UserRole role, List<DisciplineEntity> disciplines, DisciplineEntity discipline, List<MonitorAvailabilityEntity> availabilities) {
        super(id, name, surname, email, password, role, disciplines);
        this.discipline = discipline;
        this.availabilities = availabilities;
    }
}
