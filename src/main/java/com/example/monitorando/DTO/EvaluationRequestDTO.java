package com.example.monitorando.DTO;

import jakarta.validation.constraints.*;
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
