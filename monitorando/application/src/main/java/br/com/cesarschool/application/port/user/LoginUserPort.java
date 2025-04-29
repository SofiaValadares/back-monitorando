package br.com.cesarschool.application.port.user;

public interface LoginUserPort<T> {
    T loginUser(String email, String password);
}
