package com.example.monitorando.DTO;

import lombok.Data;

@Data
public class QuestionRequestDTO {
    private Long studentId;
    private Long disciplineId;
    private Long monitorId;
    private String title;
    private String content;
    private Boolean isPublic = true;
}