package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.WeekDay;

import java.time.LocalTime;


public class AvailableTimeEntity {

    private final Long id;
    private final WeekDay weekDay;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public AvailableTimeEntity(Long id, WeekDay weekDay, LocalTime startTime, LocalTime endTime) {
        if (weekDay == null) {
            throw new IllegalArgumentException("O dia da semana não pode ser nulo.");
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

        this.id = id;
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
