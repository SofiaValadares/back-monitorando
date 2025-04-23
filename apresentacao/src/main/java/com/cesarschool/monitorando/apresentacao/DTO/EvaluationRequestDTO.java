package com.cesarschool.monitorando.apresentacao.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvaluationRequestDTO {

    @NotNull
    private Long evaluatorId;

    @NotNull
    private Long evaluatedId;

    private Long appointmentId;
    private Long questionId;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;

    private String comment;
}
