package com.example.monitorando.DTO;

import java.util.List;

import lombok.Data;

@Data
public class MonitorAvailabilityRequest {
    private Long monitorId;
    private List<AvailabilityDTO> availability;

    @Data
    public static class AvailabilityDTO {
        private String dayOfWeek;
        private String startTime; // "14:00"
        private String endTime;   // "16:00"
    }
}
