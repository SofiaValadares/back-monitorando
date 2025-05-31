package br.com.cesarschool.infrastructure.adapter.evaluation;

import br.com.cesarschool.domain.entity.EvaluationEntity;
import br.com.cesarschool.domain.repository.evaluation.EvaluateStudentPort;
import br.com.cesarschool.infrastructure.persistence.entity.EvaluationJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.EvaluationMapper;
import br.com.cesarschool.infrastructure.repository.EvaluationJpaRepository;
import br.com.cesarschool.infrastructure.repository.MonitorJpaRepository;
import br.com.cesarschool.infrastructure.repository.StudentJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EvaluateStudentAdapter implements EvaluateStudentPort {

    private final EvaluationJpaRepository evaluationRepository;
    private final StudentJpaRepository studentRepository;
    private final MonitorJpaRepository monitorRepository;

    public EvaluateStudentAdapter(
        EvaluationJpaRepository evaluationRepository,
        StudentJpaRepository studentRepository,
        MonitorJpaRepository monitorRepository
    ) {
        this.evaluationRepository = evaluationRepository;
        this.studentRepository = studentRepository;
        this.monitorRepository = monitorRepository;
    }

    @Override
    public void evaluateStudent(Long studentId, Long monitorId, LocalDate attendanceDate, int rating, String comment) {
        EvaluationEntity evaluation = new EvaluationEntity(studentId, monitorId, attendanceDate, rating, comment);

        StudentJpaEntity student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        MonitorJpaEntity monitor = monitorRepository.findById(monitorId)
            .orElseThrow(() -> new IllegalArgumentException("Monitor não encontrado"));

        EvaluationJpaEntity jpaEntity = EvaluationMapper.toJpa(evaluation, monitor, student);

        evaluationRepository.save(jpaEntity);
    }
}
