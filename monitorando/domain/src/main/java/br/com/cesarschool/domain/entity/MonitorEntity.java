package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MonitorEntity extends StudentEntity {

    // Lista de horários de atendimento (aluno agenda)
    private List<AvailableTimeEntity> availableTimes;

    // Lista de horários de monitoria (monitor oferece)
    private List<AvailableTimeEntity> monitorSchedule;

    public MonitorEntity(
            Long id,
            String name,
            String surname,
            String email,
            String password,
            UserRole role,
            List<DisciplineEntity> disciplines,
            List<AvailableTimeEntity> availableTimes,
            List<AvailableTimeEntity> monitorSchedule
    ) {
        super(id, name, surname, email, password, role, disciplines);
        this.availableTimes = availableTimes;
        this.monitorSchedule = monitorSchedule;
    }
}
