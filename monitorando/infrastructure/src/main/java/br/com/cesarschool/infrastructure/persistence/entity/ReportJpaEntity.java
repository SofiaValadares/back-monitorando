package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reports")
public class ReportJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate attendanceDate;
    private String subject;
    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentJpaEntity student;

    @ManyToOne
    @JoinColumn(name = "monitor_id")
    private MonitorJpaEntity monitor;

    public ReportJpaEntity() {
    }

    public ReportJpaEntity(
        Long id,
        LocalDate attendanceDate,
        String subject,
        String description,
        StudentJpaEntity student,
        MonitorJpaEntity monitor
    ) {
        this.id = id;
        this.attendanceDate = attendanceDate;
        this.subject = subject;
        this.description = description;
        this.student = student;
        this.monitor = monitor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
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
