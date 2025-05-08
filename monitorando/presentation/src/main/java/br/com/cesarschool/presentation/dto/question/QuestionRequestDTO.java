package br.com.cesarschool.presentation.dto.question;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequestDTO {
    private Long studentId;
    private String question;
    private Long disciplineId;
    private Long monitorId;
}
