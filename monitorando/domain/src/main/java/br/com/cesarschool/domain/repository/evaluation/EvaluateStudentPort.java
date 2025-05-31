package br.com.cesarschool.domain.repository.evaluation;

import java.time.LocalDate;

public interface EvaluateStudentPort {
    void evaluateStudent(Long studentId, Long monitorId, LocalDate attendanceDate, int rating, String comment);
}
