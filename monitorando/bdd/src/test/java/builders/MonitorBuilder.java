package builders;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.enums.UserRole;

import java.util.List;

public class MonitorBuilder {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserRole role;
    private List<DisciplineEntity> disciplines;
    private List<AvailableTimeEntity> availableTimes;
    private DisciplineEntity disciplineMonitor;

    public MonitorBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public MonitorBuilder comNome(String name) {
        this.name = name;
        return this;
    }

    public MonitorBuilder comSobrenome(String surname) {
        this.surname = surname;
        return this;
    }

    public MonitorBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public MonitorBuilder comSenha(String password) {
        this.password = password;
        return this;
    }

    public MonitorBuilder comPapel(UserRole role) {
        this.role = role;
        return this;
    }

    public MonitorBuilder comDisciplinas(List<DisciplineEntity> disciplines) {
        this.disciplines = disciplines;
        return this;
    }

    public MonitorBuilder comHorariosDisponiveis(List<AvailableTimeEntity> availableTimes) {
        this.availableTimes = availableTimes;
        return this;
    }

    public MonitorBuilder comDisciplinaMonitor(DisciplineEntity disciplineMonitor) {
        this.disciplineMonitor = disciplineMonitor;
        return this;
    }

    public MonitorEntity build() {
        return new MonitorEntity(id, name, surname, email, password, role, disciplines, availableTimes, disciplineMonitor);
    }
}