package br.com.cesarschool.domain.entity;

import java.time.LocalDateTime;

public class AttendanceEntity {

    public enum Status {
    	PENDENTE,
        AGENDADO,
        REALIZADO,
        CANCELADO
    }

    private Long id;
    private Long studentId;
    private Long monitorId;
    private LocalDateTime scheduledDateTime;
    private Status status;

    public AttendanceEntity() {}

    public AttendanceEntity(Long id, Long studentId, Long monitorId, LocalDateTime scheduledDateTime, Status status) {
        this.id = id;
        this.studentId = studentId;
        this.monitorId = monitorId;
        this.scheduledDateTime = scheduledDateTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isRealized() {
        return this.status == Status.REALIZADO;
    }
}
