package br.com.cesarschool.domain.service;

import br.com.cesarschool.application.port.discipline.FindDisciplinePort;
import br.com.cesarschool.application.port.question.FindQuestionPort;
import br.com.cesarschool.application.port.question.QuestionChatPort;
import br.com.cesarschool.application.port.question.QuestionToMonitorPort;
import br.com.cesarschool.application.port.user.FindMonitorPort;
import br.com.cesarschool.application.port.user.FindStudentPort;
import br.com.cesarschool.domain.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final FindQuestionPort<QuestionEntity> findQuestionPort;
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
        QuestionEntity question = findQuestionPort.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Pergunta não encontrada com ID: " + questionId));

        StudentEntity student = question.getStudent();
        DisciplineEntity discipline = question.getDiscipline();

        UserEntity user = discipline.findUserInDiscipline(userId)
            .orElseThrow(() -> new IllegalArgumentException("Usuario com ID " + userId + " não esta listado na disciplina"));

        if (!question.getIsPublic()) {
            if (user instanceof StudentEntity && user != student) {
                throw new IllegalArgumentException("Pergunta privada, o usuario com ID " + userId + " não tem acesso a ela");
            }

            if (question.getMonitor() != null) {
                MonitorEntity monitor = question.getMonitor();

                if (user instanceof MonitorEntity && user != monitor) {
                    throw new IllegalArgumentException("Pergunta privada, o usuario com ID " + userId + " não tem acesso a ela");
                }
            }
        }

        questionChatPort.sendAnswerQuestion(questionId, userId, answer);
    }


}
