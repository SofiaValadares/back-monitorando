package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.entity.*;
import br.com.cesarschool.domain.repository.discipline.FindDisciplineRepository;
import br.com.cesarschool.domain.repository.question.FindQuestionRepository;
import br.com.cesarschool.domain.repository.question.QuestionChatRepository;
import br.com.cesarschool.domain.repository.question.QuestionToMonitorRepository;
import br.com.cesarschool.domain.repository.user.FindMonitorRepository;
import br.com.cesarschool.domain.repository.user.FindStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final FindQuestionRepository<QuestionEntity> findQuestionRepository;
    private final QuestionToMonitorRepository questionToMonitorRepository;
    private final QuestionChatRepository questionChatRepository;
    private final FindStudentRepository<StudentEntity> findStudentRepository;
    private final FindMonitorRepository<MonitorEntity> findMonitorRepository;
    private final FindDisciplineRepository<DisciplineEntity> findDisciplineRepository;


    public void makeQuestionToMonitor(Long studentId, String question, Long disciplineId, Long monitorId) {
        StudentEntity student = findStudentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado com ID: " + studentId));

        if (question.isBlank() || question.isEmpty()) {
            throw new IllegalArgumentException("Pergunta esta vazia");
        }

        DisciplineEntity discipline = findDisciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + disciplineId));

        MonitorEntity monitor = findMonitorRepository.findById(monitorId)
                .orElseThrow(() -> new IllegalArgumentException("Monitor não encontrada com ID: " + monitorId));

        if (!discipline.getMonitors().contains(monitor)) {
            throw new IllegalArgumentException("Monitor com ID " + monitorId +" não pertertence a disciplina com ID " + disciplineId);
        }

        questionToMonitorRepository.makeQuestionToMonitor(studentId, question, disciplineId, monitorId);
    }

    public void sendAnswerToQuestion(Long questionId, Long userId, String answer) {
        QuestionEntity question = findQuestionRepository.findById(questionId)
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

        questionChatRepository.sendAnswerQuestion(questionId, userId, answer);
    }


}
