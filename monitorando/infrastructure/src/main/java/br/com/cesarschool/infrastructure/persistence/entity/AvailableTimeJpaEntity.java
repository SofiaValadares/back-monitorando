package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalTime;

import br.com.cesarschool.domain.entity.enums.WeekDay;

@Entity
@Table(name = "available_times")
public class AvailableTimeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WeekDay dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "monitor_attendance_id")
    private MonitorJpaEntity monitorForAttendance;

    // Este campo é usado por monitorSchedule (monitorias)
    @ManyToOne
    @JoinColumn(name = "monitor_schedule_id")
    private MonitorJpaEntity monitorForSchedule;

    public AvailableTimeJpaEntity() {}

    public AvailableTimeJpaEntity(Long id, WeekDay dayOfWeek, LocalTime startTime, LocalTime endTime, MonitorJpaEntity monitorForAttendance, MonitorJpaEntity monitorForSchedule) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.monitorForAttendance = monitorForAttendance;
        this.monitorForSchedule = monitorForSchedule;
    }



    public Long getId() {
        return id;
    }

    public WeekDay getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public MonitorJpaEntity getMonitorForAttendance() {
        return monitorForAttendance;
    }

    public MonitorJpaEntity getMonitorForSchedule() {
        return monitorForSchedule;
    }

    public void setMonitorForSchedule(MonitorJpaEntity monitor) {
        this.monitorForSchedule = monitor;
    }
}
