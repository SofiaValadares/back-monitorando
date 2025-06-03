package br.com.cesarschool.application.service;

import br.com.cesarschool.domain.entity.*;
import br.com.cesarschool.domain.repository.discipline.FindDisciplineRepository;
import br.com.cesarschool.domain.repository.question.FindQuestionRepository;
import br.com.cesarschool.domain.repository.question.QuestionChatRepository;
import br.com.cesarschool.domain.repository.question.QuestionToMonitorRepository;
import br.com.cesarschool.domain.repository.user.FindMonitorRepository;
import br.com.cesarschool.domain.repository.user.FindStudentRepository;
import org.springframework.stereotype.Service;

@Service

public class QuestionService {

    private final FindQuestionRepository<QuestionEntity> findQuestionRepository;
    private final QuestionToMonitorRepository questionToMonitorRepository;
    private final QuestionChatRepository questionChatRepository;
    private final FindStudentRepository findStudentRepository;
    private final FindMonitorRepository<MonitorEntity> findMonitorRepository;
    private final FindDisciplineRepository findDisciplineRepository;

    public QuestionService(FindQuestionRepository<QuestionEntity> findQuestionRepository, QuestionToMonitorRepository questionToMonitorRepository, QuestionChatRepository questionChatRepository, FindStudentRepository findStudentRepository, FindMonitorRepository<MonitorEntity> findMonitorRepository, FindDisciplineRepository findDisciplineRepository) {
        this.findQuestionRepository = findQuestionRepository;
        this.questionToMonitorRepository = questionToMonitorRepository;
        this.questionChatRepository = questionChatRepository;
        this.findStudentRepository = findStudentRepository;
        this.findMonitorRepository = findMonitorRepository;
        this.findDisciplineRepository = findDisciplineRepository;
    }

    public void makeQuestionToMonitor(Long studentId, String question, Long disciplineId, Long monitorId) {
        StudentEntity student = findStudentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado com ID: " + studentId));

        if (question.isBlank() || question.isEmpty()) {
            throw new IllegalArgumentException("Pergunta esta vazia");
        }

        DisciplineEntity discipline = findDisciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + disciplineId));

        if (!discipline.getMonitorsIds().contains(monitorId)) {
            throw new IllegalArgumentException("Monitor com ID " + monitorId +" não pertertence a disciplina com ID " + disciplineId);
        }

        questionToMonitorRepository.makeQuestionToMonitor(studentId, question, disciplineId, monitorId);
    }

    public void sendAnswerToQuestion(Long questionId, Long userId, String answer) {
        QuestionEntity question = findQuestionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Pergunta não encontrada com ID: " + questionId));

        DisciplineEntity discipline = findDisciplineRepository.findById(question.getDisciplineId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + question.getDisciplineId()));

        if (!(discipline.getStudentsIds().contains(userId) || discipline.getMonitorsIds().contains(userId))) {
            throw new IllegalArgumentException("Usuario " + userId + " não esta lsitado na disciplina");
        }

        if (!question.getPublic()) {
            if (question.getStudentId() == userId) {
                throw new IllegalArgumentException("Pergunta privada, o usuario com ID " + userId + " não tem acesso a ela");
            }

            if (question.getMonitorId() != null) {
                Long monitor = question.getMonitorId();

                if (monitor == userId) {
                    throw new IllegalArgumentException("Pergunta privada, o usuario com ID " + userId + " não tem acesso a ela");
                }
            }
        }

        questionChatRepository.sendAnswerQuestion(questionId, userId, answer);
    }


}
