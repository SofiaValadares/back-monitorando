package br.com.cesarschool.application.port.user;

public interface RegisterUserPort {
    void register(String name, String surname, String email, String password, String role);
}
