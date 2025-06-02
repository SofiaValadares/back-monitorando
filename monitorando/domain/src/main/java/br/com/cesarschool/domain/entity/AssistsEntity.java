package br.com.cesarschool.domain.entity;

import java.time.LocalTime;
import java.util.Date;


public class AssistsEntity {
    private final StudentEntity student;
    private final MonitorEntity monitor;
    private final Date day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public AssistsEntity(StudentEntity student, MonitorEntity monitor, Date day, LocalTime startTime, LocalTime endTime) {
        if (student == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo.");
        }
        if (monitor == null) {
            throw new IllegalArgumentException("O monitor não pode ser nulo.");
        }
        if (day == null) {
            throw new IllegalArgumentException("O dia do atendimento não pode ser nulo.");
        }
        if (startTime == null) {
            throw new IllegalArgumentException("O horário de início não pode ser nulo.");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("O horário de fim não pode ser nulo.");
        }
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("O horário de início deve ser anterior ao horário de fim.");
        }

        this.student = student;
        this.monitor = monitor;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public MonitorEntity getMonitor() {
        return monitor;
    }

    public Date getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
