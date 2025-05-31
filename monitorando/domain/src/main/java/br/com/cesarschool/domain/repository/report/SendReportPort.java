package br.com.cesarschool.domain.repository.report;

import java.time.LocalDate;

public interface SendReportPort {
    void sendReport(Long studentId, Long monitorId, LocalDate attendanceDate, String subject, String description);
}