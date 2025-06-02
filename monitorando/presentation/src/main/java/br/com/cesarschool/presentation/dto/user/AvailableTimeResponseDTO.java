package br.com.cesarschool.presentation.dto.user;

import br.com.cesarschool.domain.entity.enums.WeekDay;

import java.time.LocalTime;


public class AvailableTimeResponseDTO {
    private Long id;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;

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
}