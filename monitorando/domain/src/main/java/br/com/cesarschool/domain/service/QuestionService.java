package br.com.cesarschool.domain.service;

import br.com.cesarschool.application.port.discipline.FindDisciplinePort;
import br.com.cesarschool.application.port.question.QuestionChatPort;
import br.com.cesarschool.application.port.question.QuestionToMonitorPort;
import br.com.cesarschool.application.port.user.FindMonitorPort;
import br.com.cesarschool.application.port.user.FindStudentPort;
import br.com.cesarschool.domain.entity.DisciplineEntity;
import br.com.cesarschool.domain.entity.MonitorEntity;
import br.com.cesarschool.domain.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionToMonitorPort questionToMonitorPort;
    private final QuestionChatPort questionChatPort;
    private final FindStudentPort<StudentEntity> findStudentPort;
    private final FindMonitorPort<MonitorEntity> findMonitorPort;
    private final FindDisciplinePort<DisciplineEntity> findDisciplinePort;


    public void makeQuestionToMonitor(Long studentId, String question, Long disciplineId, Long monitorId) {
        StudentEntity student = findStudentPort.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado com ID: " + studentId));

        if (question.isBlank() || question.isEmpty()) {
            throw new IllegalArgumentException("Pergunta esta vazia");
        }

        DisciplineEntity discipline = findDisciplinePort.findById(disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + disciplineId));

        MonitorEntity monitor = findMonitorPort.findById(monitorId)
                .orElseThrow(() -> new IllegalArgumentException("Monitor não encontrada com ID: " + monitorId));

        if (!discipline.getMonitors().contains(monitor)) {
            throw new IllegalArgumentException("Monitor com ID " + monitorId +" não pertertence a disciplina com ID " + disciplineId);
        }

        questionToMonitorPort.makeQuestionToMonitor(studentId, question, disciplineId, monitorId);
    }

    public void sendAnswerToQuestion(Long questionId, Long userId, String answer) {
        questionChatPort.sendAnswerQuestion(questionId, userId, answer);
    }


}
