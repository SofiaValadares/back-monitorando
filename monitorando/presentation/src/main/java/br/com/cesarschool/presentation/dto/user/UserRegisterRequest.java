package br.com.cesarschool.presentation.dto.user;


public record UserRegisterRequest (
        String name,
        String surname,
        String email,
        String password,
        String role
) {
}
