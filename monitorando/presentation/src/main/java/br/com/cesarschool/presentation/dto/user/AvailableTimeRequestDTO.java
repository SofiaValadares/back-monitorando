package br.com.cesarschool.presentation.dto.user;


import br.com.cesarschool.domain.entity.enums.WeekDay;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AvailableTimeRequestDTO {
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
}