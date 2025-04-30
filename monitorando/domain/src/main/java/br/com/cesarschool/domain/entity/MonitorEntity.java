package br.com.cesarschool.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorEntity extends StudentEntity{
    private DisciplineEntity discipline;
    private List<MonitorAvailabilityEntity> availabilities;

}
