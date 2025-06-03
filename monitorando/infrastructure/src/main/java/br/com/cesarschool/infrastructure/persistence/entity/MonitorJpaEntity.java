package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "monitors")
public class MonitorJpaEntity {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private StudentJpaEntity user;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private DisciplineJpaEntity discipline;

    @OneToMany(mappedBy = "monitorForAttendance", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTimeJpaEntity> availableTimes;

    public MonitorJpaEntity() {}

    public MonitorJpaEntity(Long id, StudentJpaEntity user, DisciplineJpaEntity discipline, List<AvailableTimeJpaEntity> availableTimes) {
        this.id = id;
        this.user = user;
        this.discipline = discipline;
        this.availableTimes = availableTimes;
    }

    public Long getId() {
        return id;
    }

    public StudentJpaEntity getUser() {
        return user;
    }

    public DisciplineJpaEntity getDiscipline() {
        return discipline;
    }

    public List<AvailableTimeJpaEntity> getAvailableTimes() {
        return availableTimes;
    }
}
