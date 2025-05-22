package br.com.cesarschool.presentation.dto.user;

import br.com.cesarschool.domain.entity.enums.WeekDay;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AvailableTimeResponseDTO {
    private Long id;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
}