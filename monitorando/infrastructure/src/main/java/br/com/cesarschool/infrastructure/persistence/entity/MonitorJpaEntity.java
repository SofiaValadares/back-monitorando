package br.com.cesarschool.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "monitors")
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

    @OneToMany(mappedBy = "monitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableTimeJpaEntity> availableTimes = new ArrayList<>();

    public MonitorJpaEntity() {
    }

    public MonitorJpaEntity(Long id, UserJpaEntity user, DisciplineJpaEntity discipline) {
        this.id = id;
        this.user = user;
        this.discipline = discipline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserJpaEntity getUser() {
        return user;
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }

    public DisciplineJpaEntity getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplineJpaEntity discipline) {
        this.discipline = discipline;
    }

    public List<AvailableTimeJpaEntity> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<AvailableTimeJpaEntity> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public void addAvailableTime(AvailableTimeJpaEntity availableTime) {
        availableTimes.add(availableTime);
        availableTime.setMonitor(this);
    }

    public void removeAvailableTime(AvailableTimeJpaEntity availableTime) {
        availableTimes.remove(availableTime);
        availableTime.setMonitor(null);
    }
}
