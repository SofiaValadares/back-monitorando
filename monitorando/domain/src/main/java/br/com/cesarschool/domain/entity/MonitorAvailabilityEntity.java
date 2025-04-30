package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.management.monitor.Monitor;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorAvailabilityEntity {
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private Monitor monitor;

}
