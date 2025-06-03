package br.com.cesarschool.presentation.dto.question;

public record QuestionCountResponseDTO(
        Integer total,
        Integer pending,
        Integer answered
) {
}
