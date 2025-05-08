package br.com.cesarschool.infrastructure.adapter.question;

import br.com.cesarschool.application.port.question.QuestionToMonitorPort;
import br.com.cesarschool.domain.entity.enums.QuestionStatus;
import br.com.cesarschool.infrastructure.persistence.entity.DisciplineJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.QuestionJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionToMonitorAdapter implements QuestionToMonitorPort {

    private final StudentJpaRepository studentRepository;
    private final DisciplineJpaRepository disciplineRepository;
    private final MonitorJpaRepository monitorRepository;
    private final QuestionJpaRepository questionRepository;

    @Override
    public void makeQuestionToMonitor(Long studentId, String questionText, Long disciplineId, Long monitorId) {
        StudentJpaEntity student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        DisciplineJpaEntity discipline = disciplineRepository.findById(disciplineId)
                .orElseThrow(() -> new IllegalArgumentException("Discipline not found"));

        MonitorJpaEntity monitor = monitorRepository.findById(monitorId)
                .orElseThrow(() -> new IllegalArgumentException("Monitor not found"));

        QuestionJpaEntity question = new QuestionJpaEntity();
        question.setStudent(student);
        question.setDiscipline(discipline);
        question.setMonitor(monitor);
        question.setQuestion(questionText);
        question.setIsPublic(false); // ou true, dependendo da lógica do seu sistema
        question.setStatus(QuestionStatus.PENDING);

        questionRepository.save(question);
    }
}
