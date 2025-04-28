package br.com.cesarschool.application.port.user;

public interface RegisterUserPort {
    void register(String name, String email, String password);
}
