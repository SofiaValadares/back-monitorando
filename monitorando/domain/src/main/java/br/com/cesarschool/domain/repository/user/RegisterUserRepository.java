package br.com.cesarschool.domain.repository.user;

public interface RegisterUserRepository {
    void register(String name, String surname, String email, String password, String role);
}
