package br.com.cesarschool.domain.entity;

import br.com.cesarschool.domain.entity.enums.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTimeEntity {

    private Long id; // <-- compatível com @Id do JPA
    private WeekDay weekDay; // precisa garantir compatibilidade no JPA
    private LocalTime startTime;
    private LocalTime endTime;
}
