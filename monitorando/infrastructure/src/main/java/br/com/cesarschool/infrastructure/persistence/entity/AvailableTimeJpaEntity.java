package br.com.cesarschool.infrastructure.persistence.entity;

import br.com.cesarschool.domain.entity.enums.WeekDay;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "available_times")
public class AvailableTimeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WeekDay weekDay;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "monitor_id", nullable = false)
    private MonitorJpaEntity monitor;

    public AvailableTimeJpaEntity() {
    }

    public AvailableTimeJpaEntity(
        Long id,
        WeekDay weekDay,
        LocalTime startTime,
        LocalTime endTime,
        MonitorJpaEntity monitor
    ) {
        this.id = id;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.monitor = monitor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public MonitorJpaEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorJpaEntity monitor) {
        this.monitor = monitor;
    }
}
