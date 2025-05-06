package br.com.cesarschool.application.port.question;

public interface QuestionToMonitorPort {
    void makeQuestionToMonitor(Long studentId, String question, Long disciplineId, Long monitorId);
}