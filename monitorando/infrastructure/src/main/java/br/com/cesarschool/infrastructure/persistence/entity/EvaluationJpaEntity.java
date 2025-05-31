package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "evaluations")
public class EvaluationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate attendanceDate;

    private int rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "monitor_id")
    private MonitorJpaEntity monitor;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentJpaEntity student;

    public EvaluationJpaEntity() {
    }

    public EvaluationJpaEntity(
        Long id,
        LocalDate attendanceDate,
        int rating,
        String comment,
        MonitorJpaEntity monitor,
        StudentJpaEntity student
    ) {
        this.id = id;
        this.attendanceDate = attendanceDate;
        this.rating = rating;
        this.comment = comment;
        this.monitor = monitor;
        this.student = student;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public MonitorJpaEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorJpaEntity monitor) {
        this.monitor = monitor;
    }

    public StudentJpaEntity getStudent() {
        return student;
    }

    public void setStudent(StudentJpaEntity student) {
        this.student = student;
    }
}
