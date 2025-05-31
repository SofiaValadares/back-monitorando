package br.com.cesarschool.domain.entity;

import java.time.LocalDate;

public class ReportEntity {

    private Long studentId;
    private Long monitorId;
    private LocalDate date;
    private String subject;
    private String description;

    public ReportEntity() {}

    public ReportEntity(Long studentId, Long monitorId, LocalDate date, String subject, String description) {
        this.studentId = studentId;
        this.monitorId = monitorId;
        this.date = date;
        this.subject = subject;
        this.description = description;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
