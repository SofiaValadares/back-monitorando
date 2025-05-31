package br.com.cesarschool.infrastructure.adapter.report;

import br.com.cesarschool.domain.entity.ReportEntity;
import br.com.cesarschool.domain.repository.report.SendReportPort;
import br.com.cesarschool.infrastructure.persistence.entity.ReportJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.MonitorJpaEntity;
import br.com.cesarschool.infrastructure.persistence.entity.StudentJpaEntity;
import br.com.cesarschool.infrastructure.persistence.mapper.ReportMapper;
import br.com.cesarschool.infrastructure.repository.ReportJpaRepository;
import br.com.cesarschool.infrastructure.repository.StudentJpaRepository;
import br.com.cesarschool.infrastructure.repository.MonitorJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SendReportAdapter implements SendReportPort {

    private final ReportJpaRepository reportRepository;
    private final StudentJpaRepository studentRepository;
    private final MonitorJpaRepository monitorRepository;

    public SendReportAdapter(
        ReportJpaRepository reportRepository,
        StudentJpaRepository studentRepository,
        MonitorJpaRepository monitorRepository
    ) {
        this.reportRepository = reportRepository;
        this.studentRepository = studentRepository;
        this.monitorRepository = monitorRepository;
    }

    @Override
    public void sendReport(Long studentId, Long monitorId, LocalDate attendanceDate, String subject, String description) {
        ReportEntity report = new ReportEntity(studentId, monitorId, attendanceDate, subject, description);

        StudentJpaEntity student = studentRepository.findById(studentId)
            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        MonitorJpaEntity monitor = monitorRepository.findById(monitorId)
            .orElseThrow(() -> new IllegalArgumentException("Monitor não encontrado"));

        ReportJpaEntity jpaEntity = ReportMapper.toJpa(report, student, monitor);

        reportRepository.save(jpaEntity);
    }
}
