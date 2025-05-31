package br.com.cesarschool.domain.service;

import br.com.cesarschool.domain.repository.attendance.FindAttendancesPort;
import br.com.cesarschool.domain.repository.report.SendReportPort;
import br.com.cesarschool.domain.entity.AttendanceEntity;

import java.time.LocalDate;
import java.util.List;

public class ReportService {

    private final SendReportPort sendReportPort;
    private final FindAttendancesPort findAttendancesPort;

    public ReportService(
        SendReportPort sendReportPort,
        FindAttendancesPort findAttendancesPort
    ) {
        this.sendReportPort = sendReportPort;
        this.findAttendancesPort = findAttendancesPort;
    }

    public void sendReport(Long studentId, Long monitorId, LocalDate attendanceDate, String subject, String description) {

        if (studentId == null || monitorId == null || attendanceDate == null ||
            subject == null || subject.isBlank() ||
            description == null || description.isBlank()) {
            throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos.");
        }

        List<AttendanceEntity> atendimentos = findAttendancesPort.findByStudentIdAndMonitorId(studentId, monitorId);
        boolean houveAtendimento = atendimentos.stream()
            .anyMatch(a -> a.getStatus() == AttendanceEntity.Status.REALIZADO);

        if (!houveAtendimento) {
            throw new IllegalStateException("Relatos só podem ser enviados após um atendimento realizado.");
        }

        sendReportPort.sendReport(studentId, monitorId, attendanceDate, subject, description);
    }
}
