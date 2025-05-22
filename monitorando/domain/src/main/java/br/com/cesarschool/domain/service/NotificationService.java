package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.entity.AvailableTimeEntity;
import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    public void notifyStudentsOfUpdatedSchedule(MonitorEntity monitor, List<AvailableTimeEntity> horarios) {
        DisciplineEntity disciplina = monitor.getDisciplineMonitor();

        if (disciplina == null || disciplina.getStudents() == null) {
            System.out.println("Nenhum aluno para notificar.");
            return;
        }

        String mensagem = buildMessage(monitor, horarios);

        for (StudentEntity aluno : disciplina.getStudents()) {
            System.out.println(" Notificação para aluno [" + aluno.getName() + "]: " + mensagem);
        }
    }

    private String buildMessage(MonitorEntity monitor, List<AvailableTimeEntity> horarios) {
        StringBuilder builder = new StringBuilder();
        builder.append("O monitor ").append(monitor.getName())
                .append(" atualizou seus horários disponíveis na disciplina ")
                .append(monitor.getDisciplineMonitor().getName()).append(".\n");
        builder.append("Novos horários:\n");

        for (AvailableTimeEntity horario : horarios) {
            builder.append("- ")
                    .append(horario.getWeekDay()).append(": ")
                    .append(horario.getStartTime()).append(" às ")
                    .append(horario.getEndTime()).append("\n");
        }

        return builder.toString();
    }
}
