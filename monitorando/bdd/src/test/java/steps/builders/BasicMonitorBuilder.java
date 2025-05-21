package steps.builders;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;
import steps.interfaces.MonitorBuilder;

import java.util.ArrayList;
import java.util.List;

public class BasicMonitorBuilder implements MonitorBuilder {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole role;
    private List<DisciplineEntity> disciplines;
    private List<AvailableTimeEntity> availableTimes;
    private DisciplineEntity disciplineMonitor;

    @Override
    public void buildId() {
        this.id = 101L;
    }

    @Override
    public void buildName() {
        this.name = "João";
    }

    @Override
    public void buildSurname() {
        this.surname = "Pereira";
    }

    @Override
    public void buildEmail() {
        this.email = "joao.pereira@cesarschool.com";
    }

    @Override
    public void buildPassword() {
        this.password = "senha123";
    }

    @Override
    public void buildRole() {
        this.role = UserRole.MONITOR;
    }

    @Override
    public void buildDisciplines() {
        DisciplineEntity discipline = new DisciplineEntity(
                1L,
                "Algoritmos",
                "ALG001",
                new ArrayList<>(),
                new ArrayList<>()
        );
        this.disciplines = List.of(discipline);
    }

    @Override
    public void buildAvailableTimes() {
        // Supondo que AvailableTimeEntity tem um construtor básico
        this.availableTimes = new ArrayList<>();
        //this.availableTimes.add(new AvailableTimeEntity("Segunda-feira", "14:00"));
        //this.availableTimes.add(new AvailableTimeEntity("Quarta-feira", "10:00"));
    }

    @Override
    public void buildDisciplineMonitor() {
        this.disciplineMonitor = new DisciplineEntity(
                1L,
                "Algoritmos",
                "ALG001",
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public MonitorEntity getMonitor() {
        return new MonitorEntity(
                id,
                name,
                surname,
                email,
                password,
                role,
                disciplines,
                availableTimes,
                disciplineMonitor
        );
    }
}