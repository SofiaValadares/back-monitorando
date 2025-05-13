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

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private DisciplineJpaEntity discipline;

    @OneToMany(mappedBy = "monitorForAttendance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTimeJpaEntity> availableTimes;
}
