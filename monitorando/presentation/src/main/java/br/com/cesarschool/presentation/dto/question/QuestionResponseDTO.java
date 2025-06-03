package br.com.cesarschool.presentation.dto.question;

public record QuestionResponseDTO(
        Long id,
        String question,
        Long studentId,
        Long disciplineId,
        Boolean isPublic,
        String status,
        Long monitorId
) {
}
