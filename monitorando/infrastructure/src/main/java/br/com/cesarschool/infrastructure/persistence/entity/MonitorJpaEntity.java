package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "monitors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorJpaEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private UserJpaEntity user;

    @ManyToMany
    @JoinTable(
        name = "monitor_discipline",
        joinColumns = @JoinColumn(name = "monitor_id"),
        inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private List<DisciplineJpaEntity> disciplines;

    @OneToMany(mappedBy = "monitorForAttendance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTimeJpaEntity> availableTimes;

    @OneToMany(mappedBy = "monitorForSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTimeJpaEntity> monitorSchedule;
}
