package br.com.cesarschool.domain.repository.question;

public interface QuestionToMonitorRepository {
    void makeQuestionToMonitor(Long studentId, String question, Long disciplineId, Long monitorId);
}