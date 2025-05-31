package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.entity.AttendanceEntity;
import br.com.cesarschool.domain.entity.EvaluationEntity;
import br.com.cesarschool.domain.repository.attendance.FindAttendancesPort;
import br.com.cesarschool.domain.repository.evaluation.EvaluateStudentPort;
import br.com.cesarschool.domain.repository.evaluation.FindEvaluationsByStudentPort;

import java.time.LocalDate;
import java.util.List;

public class EvaluationService {

    private final EvaluateStudentPort evaluateStudentPort;
    private final FindEvaluationsByStudentPort findEvaluationsByStudentPort;
    private final FindAttendancesPort findAttendancesPort;

    public EvaluationService(
        EvaluateStudentPort evaluateStudentPort,
        FindEvaluationsByStudentPort findEvaluationsByStudentPort,
        FindAttendancesPort findAttendancesPort
    ) {
        this.evaluateStudentPort = evaluateStudentPort;
        this.findEvaluationsByStudentPort = findEvaluationsByStudentPort;
        this.findAttendancesPort = findAttendancesPort;
    }

    public void evaluate(Long studentId, Long monitorId, LocalDate attendanceDate, int rating, String comment) {

        // ✅ Validação dos campos obrigatórios
        if (studentId == null || monitorId == null || attendanceDate == null || comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos.");
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 5.");
        }

        if (attendanceDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível avaliar antes da data do atendimento.");
        }

        // ✅ Verificar se já houve atendimento entre o aluno e o monitor
        List<AttendanceEntity> atendimentos = findAttendancesPort.findByStudentIdAndMonitorId(studentId, monitorId);
        boolean houveAtendimentoRealizado = atendimentos.stream()
                .anyMatch(att -> att.getStatus() == AttendanceEntity.Status.REALIZADO);

        if (!houveAtendimentoRealizado) {
            throw new IllegalStateException("Não é possível avaliar um aluno sem ter realizado atendimento.");
        }

        // (Opcional) verificar se já foi avaliado anteriormente
        List<EvaluationEntity> existingEvaluations = findEvaluationsByStudentPort.findByStudentId(studentId);
        boolean jaAvaliado = existingEvaluations.stream()
                .anyMatch(e -> e.getMonitorId().equals(monitorId) && e.getAttendanceDate().isEqual(attendanceDate));

        if (jaAvaliado) {
            throw new IllegalStateException("Este atendimento já foi avaliado.");
        }

        // ✅ Criar e enviar avaliação
        evaluateStudentPort.evaluateStudent(studentId, monitorId, attendanceDate, rating, comment);
    }

    public List<EvaluationEntity> getEvaluationsByStudent(Long studentId) {
        return findEvaluationsByStudentPort.findByStudentId(studentId);
    }
}
