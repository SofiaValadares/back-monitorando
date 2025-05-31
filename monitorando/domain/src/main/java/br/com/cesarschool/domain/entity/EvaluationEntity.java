package br.com.cesarschool.domain.entity;

import java.time.LocalDate;

public class EvaluationEntity {

    private Long studentId;
    private Long monitorId;
    private LocalDate attendanceDate;
    private int rating;
    private String comment;

    public EvaluationEntity() {}

    public EvaluationEntity(Long studentId, Long monitorId, LocalDate attendanceDate, int rating, String comment) {
        this.studentId = studentId;
        this.monitorId = monitorId;
        this.attendanceDate = attendanceDate;
        this.rating = rating;
        this.comment = comment;
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
}
