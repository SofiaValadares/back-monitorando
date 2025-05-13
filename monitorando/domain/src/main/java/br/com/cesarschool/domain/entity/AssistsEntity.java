package br.com.cesarschool.domain.entity;

import java.time.LocalTime;
import java.util.Date;

public class AssistsEntity {
    private StudentEntity student;
    private MonitorEntity monitor;
    private Date day;
    private LocalTime startTime;
    private LocalTime endTime;
}
