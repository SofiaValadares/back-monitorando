package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

import br.com.cesarschool.domain.entity.enums.WeekDay;

@Entity
@Table(name = "available_times")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public void setMonitorForSchedule(MonitorJpaEntity monitor) {
        this.monitorForSchedule = monitor;
    }

}
