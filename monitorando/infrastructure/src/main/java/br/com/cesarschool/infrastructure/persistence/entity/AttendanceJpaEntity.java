package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendances")
public class AttendanceJpaEntity {

    public enum Status {
        PENDENTE,
        AGENDADO,
        REALIZADO,
        CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime scheduledDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentJpaEntity student;

    @ManyToOne
    @JoinColumn(name = "monitor_id", nullable = false)
    private MonitorJpaEntity monitor;

    public AttendanceJpaEntity() {
    }

    public AttendanceJpaEntity(Long id, LocalDateTime scheduledDateTime, Status status, StudentJpaEntity student, MonitorJpaEntity monitor) {
        this.id = id;
        this.scheduledDateTime = scheduledDateTime;
        this.status = status;
        this.student = student;
        this.monitor = monitor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StudentJpaEntity getStudent() {
        return student;
    }

    public void setStudent(StudentJpaEntity student) {
        this.student = student;
    }

    public MonitorJpaEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorJpaEntity monitor) {
        this.monitor = monitor;
    }
}
