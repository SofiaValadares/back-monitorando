package br.com.cesarschool.presentation.dto.user;

public record StudentAddDisciplineRequest(
        Long idStudent,
        String codeDiscipline
) {
}
